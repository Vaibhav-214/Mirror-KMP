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
import onboarding.util.isValidDate
import presentation.theme.backGroundGray
import presentation.theme.colorAccent
import presentation.theme.textColorDark
import presentation.theme.white

class BirthDateScreen(private val viewModel: JourneyViewModel): Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        var birthdate by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
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
                text = "When's your birthday?",
                style = MaterialTheme.typography.h2
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "To make sure we recommend the right" +
                        "type of guidance for you.",
                style = MaterialTheme.typography.subtitle1
            )

            Spacer(Modifier.height(28.dp))

            OnboardingTextField(
                value = birthdate,
                onValueChange = { birthdate = it },
                placeHolderText = "09/02/1999",
                keyboardType = KeyboardType.Text
            )


            Spacer(Modifier.weight(1f))

            ContinueButton(
                text = "Next",
                backgroundColor = colorAccent,
                textColor = white,
                enabled = isValidDate(birthdate),
                onClick = {
                    viewModel.updateProgress(true)
                    navigator.push(GenderScreen(viewModel))
                }
            )

            Spacer(Modifier.height(8.dp))

            ContinueButton(
                text = "Skip",
                backgroundColor = backGroundGray,
                textColor = textColorDark,
                onClick = {
                    viewModel.updateProgress(true)
                    navigator.push(GenderScreen(viewModel))
                }
            )

        }
    }
}