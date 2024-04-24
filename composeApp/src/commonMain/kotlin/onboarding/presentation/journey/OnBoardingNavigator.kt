package onboarding.presentation.journey

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import presentation.theme.colorAccent

class OnBoardingNavigator(private val journeyViewModel: JourneyViewModel) : Screen {
    @Composable
    override fun Content() {

        println("I am running 1 ")
        LaunchedEffect(Unit) {
            journeyViewModel.updateProgress(true)
        }

        Navigator(WelcomeScreen(journeyViewModel)) { navigator ->
            val progress by remember { journeyViewModel.journeyProgress }.collectAsState()
            val animatedWidth by animateDpAsState(
                targetValue = progress.dp,
                animationSpec = tween(durationMillis = 300) // Customize the animation spec as needed
            )
            Scaffold(
                modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.systemBars),
                topBar = {
                    Divider(
                        modifier = Modifier.width(animatedWidth),
                        thickness = 9.dp,
                        color = colorAccent
                    )
                }
            ) {
                SlideTransition(
                    navigator = navigator,
                    modifier = Modifier.padding(it)
                )

            }
        }
    }
}