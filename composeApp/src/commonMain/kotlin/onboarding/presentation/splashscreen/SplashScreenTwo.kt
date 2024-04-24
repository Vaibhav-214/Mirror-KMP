package onboarding.presentation.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import screens.splash.SplashScreen

class SplashScreenTwo : Screen {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        var loadNextScreen by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            delay(500)
            loadNextScreen = true
        }

        if (loadNextScreen) {
            navigator.push(SplashScreen())
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(38.dp),
                    painter = painterResource("MirrorLogo.png"),
                    contentDescription = null,
                )

                Spacer(Modifier.width(10.dp))

                Image(
                    modifier = Modifier.width(150.dp).height(37.dp),
                    painter = painterResource("MirrorNameLogo.png"),
                    contentDescription = null,
                )
            }
        }
    }
}