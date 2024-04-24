package onboarding.presentation.journey

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import onboarding.presentation.components.ContinueButton
import onboarding.presentation.components.OnBoardingTopAppBar
import onboarding.presentation.components.OnboardingTextField
import presentation.theme.colorAccent
import presentation.theme.white

class NameScreen(private val viewModel: JourneyViewModel): Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        var name by remember { mutableStateOf("") }

            Column (
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = 16.dp)
            ){

                OnBoardingTopAppBar(
                    navigationIconResource = "",
                    title = "",
                    backClick = {
                        viewModel.updateProgress(false)
                        navigator.pop()
                    }
                )

                Spacer(Modifier.height(70.dp))

                Text(
                    text = "What should we call you?",
                    style = MaterialTheme.typography.h2
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "You can tell us your name or petname or an alias," +
                            " promise it will be our little secret",
                    style = MaterialTheme.typography.subtitle1
                )

                Spacer(Modifier.height(28.dp))

                OnboardingTextField(
                    value = name,
                    onValueChange = { name = it},
                    placeHolderText = "Name",
                    keyboardType = KeyboardType.Text
                )


                Spacer(Modifier.weight(1f))

                ContinueButton(
                    text = "Next",
                    backgroundColor = colorAccent,
                    textColor = white,
                    enabled = name.isNotEmpty(),
                    onClick = {
                        viewModel.updateProgress(true)
                        navigator.push(BirthDateScreen(viewModel))
                    }
                )

            }
        }
}