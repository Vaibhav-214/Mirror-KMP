package screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import authentication.domain.AuthResponse
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import io.github.jan.supabase.compose.auth.ui.ProviderButtonContent
import io.github.jan.supabase.gotrue.providers.Google
import org.koin.compose.koinInject
import screens.home.HomeScreenContainer

//in voyager for each screen we need to implement a Screen interface

class LoginScreenContainer(val viewModel: AuthViewModel): Screen {

    @OptIn(SupabaseExperimental::class)
    @Composable
    override fun Content() {

//        val viewModel: AuthViewModel = koinInject()

        val navigator = LocalNavigator.currentOrThrow

        val uiState by remember {viewModel.userState} .collectAsState()


        val action = koinInject<SupabaseClient>().composeAuth.rememberSignInWithGoogle(
            onResult = { result ->
                viewModel.checkGoogleLoginStatus(result)
            },
            fallback = {
                viewModel.updateUserState(AuthResponse.Failure(Exception("Fallback Error")))
                println("fallback error")
            }
        )

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            when(val state = uiState) {
                null -> {
                    OutlinedButton(
                        onClick = { action.startFlow()},
                        content = { ProviderButtonContent(Google) }
                    )

                    Button(modifier = Modifier, onClick = {

                    }){
                        Text("Testing",
                            style = MaterialTheme.typography.h1)
                    }
                }
                is AuthResponse.Loading -> {
                    CircularProgressIndicator()
                }
                is AuthResponse.Success -> {
                    navigator.push(HomeScreenContainer(authViewModel = viewModel))
                }
                is AuthResponse.Failure-> {
                    Text("${state.e.message}")
                }
            }
        }

    }
}