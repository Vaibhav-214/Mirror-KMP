package di

import authentication.domain.AuthenticationRepo
import authentication.data.AuthenticationRepoImpl
import authentication.presentation.AuthenticationViewModel
import com.russhwolf.settings.Settings
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.appleNativeLogin
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import journal.data.JournalDataSource
import journal.data.SqldelightJournalDataSource
import journal.presentation.journal_detail_screen.JournalDetailsViewModel
import journal.presentation.journal_list_screen.AllJournalViewModel
import onboarding.presentation.journey.JourneyViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import screens.auth.AuthViewModel
import util.Constants
import util.viewModelDefinition

val appModule = module {
    //don't use named here or it gives error on running
    single{ Settings() }
}

val supabaseModule = module {
    single {
        createSupabaseClient(
            supabaseUrl = Constants.PROJECT_URL,
            supabaseKey = Constants.API_KEY
        ) {
            install(Auth)
            install(ComposeAuth) {
                googleNativeLogin(Constants.GOOGLE_CLIENT_ID)
                appleNativeLogin()
            }
            install(Postgrest)
        }
    }
}

val viewModelModule = module {
    viewModelDefinition { AllJournalViewModel(get()) }
    viewModelDefinition { JournalDetailsViewModel(get(), get(), get()) }
    viewModelDefinition { AuthViewModel(get()) }
    viewModelDefinition { JourneyViewModel() }
    viewModelDefinition { AuthenticationViewModel(get()) }
}

val repositoryModule = module {
    single<AuthenticationRepo> { AuthenticationRepoImpl(get(), get()) }
    single<JournalDataSource> { SqldelightJournalDataSource(get()) }
}
fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        viewModelModule,
        repositoryModule,
        supabaseModule,
        appModule,
        platformModule(),
    )
}

//function to be called in swift as KoinKt.doInitKoinIos()
fun initKoinIos(nativeModule: Module) = startKoin{
    modules(
        nativeModule,
        viewModelModule,
        repositoryModule,
        supabaseModule,
        appModule,
        platformModule(),
    )
}

