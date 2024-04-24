package onboarding.presentation.journey

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import onboarding.presentation.components.ContinueButton
import presentation.theme.colorAccent
import presentation.theme.white

class WelcomeScreen(private val viewModel: JourneyViewModel): Screen {
    @Composable
    override fun Content() {

        println(" 2")
        val navigator = LocalNavigator.currentOrThrow

            Column (
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = 16.dp)
            ){
                Spacer(Modifier.height(135.dp))

                Text(
                    text = "Welcome",
                    style = MaterialTheme.typography.h1
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "Mirror is your personal guide to self-discovery." +
                            "Unlock your emotions, track progress, and cultivate a" +
                            " life of meaning.",
                    style = MaterialTheme.typography.subtitle1
                )

                Spacer(Modifier.weight(1f))

                ContinueButton(
                    text = "Get Started",
                    backgroundColor = colorAccent,
                    textColor = white,
                    onClick = {
                        viewModel.updateProgress(true)
                        navigator.push(NameScreen(viewModel))
                    }
                )
            }
    }
}