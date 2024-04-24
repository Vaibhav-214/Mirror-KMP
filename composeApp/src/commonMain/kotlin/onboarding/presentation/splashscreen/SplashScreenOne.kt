package onboarding.presentation.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


//Not using insets on this screen as all content is in center
//TODO 1.Probably need to add transparent color in themes.xml
// 2. Figure out if there is a way to use svg files
// 3.The two splash screens can be combined to make animation later(no time right now)
class SplashScreenOne: Screen {
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
            navigator.push(SplashScreenTwo())
        }

       Box(
           modifier = Modifier
               .fillMaxSize()
               .background(color = Color(0xFF06C270)),
           contentAlignment = Alignment.Center
           ) {
           Image(
               modifier = Modifier.size(92.dp),
               painter = painterResource("MirrorLogo.png"),
               contentDescription = null
           )

       }
    }
}
