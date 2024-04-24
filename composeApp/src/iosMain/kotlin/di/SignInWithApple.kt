package di

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.Apple
import io.github.jan.supabase.gotrue.providers.builtin.IDToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import util.getKoinInstance

class SignInWithApple  {
    val supabaseCLient = getKoinInstance<SupabaseClient>()

     fun onSignInResponse(idToken: String) {
         CoroutineScope(Dispatchers.Default).launch {
             supabaseCLient.auth.signInWith(IDToken) {
                 this.provider = Apple
                 this.idToken = idToken
             }
         }
        println("on Sign in response")
    }
}

