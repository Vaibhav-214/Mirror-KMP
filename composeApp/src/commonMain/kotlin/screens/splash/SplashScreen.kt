package screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import getPlatform
import org.koin.compose.koinInject
import screens.auth.AuthViewModel
import screens.auth.LoginScreenContainer
import screens.home.HomeScreenContainer

class SplashScreen: Screen {

    @Composable
    override fun Content() {
        val authViewModel: AuthViewModel = koinInject()

        val navigator = LocalNavigator.currentOrThrow

        val isUserLoggedIn by remember { authViewModel.loginState}.collectAsState()

        if (getPlatform() == "IOS") {
            println("inside ios splash code")
            authViewModel.updateUserExistsOnIos()
            navigator.push(HomeScreenContainer(authViewModel))
        }else {
            LaunchedEffect(Unit) {
                authViewModel.isUserLoggedIn()
            }
        }




        //if this takes some time then a blank screen will appear until result is ready
        //till then we will show splash screen

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Splash Screen......", fontSize = 30.sp)
        }
        isUserLoggedIn?.let {loggedIn ->
            if (loggedIn) {
                navigator.push(HomeScreenContainer(authViewModel))
            } else {
                //handle login/signIn
                navigator.push(LoginScreenContainer(authViewModel))
            }
        }
    }
}