package onboarding.presentation.journey

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import onboarding.presentation.components.ContinueButton
import onboarding.presentation.components.OnBoardingTopAppBar
import presentation.theme.backGroundGray
import presentation.theme.backGroundWhite
import presentation.theme.colorAccent
import presentation.theme.textColorDark
import presentation.theme.white

class GenderScreen(private val viewModel: JourneyViewModel): Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow


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
                    text = "What gender do you\nidentify with?",
                    style = MaterialTheme.typography.h2
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "To receiver guidance that's relevant to your life experience.",
                    style = MaterialTheme.typography.subtitle1
                )

                Spacer(Modifier.height(37.dp))

                GenderSelectionGroup()

                Spacer(Modifier.weight(1f))

                ContinueButton(
                    text = "Next",
                    backgroundColor = colorAccent,
                    textColor = white,
                    onClick = {
                        viewModel.updateProgress(true)
                        navigator.push(AddressScreen(viewModel))
                    }
                )

                Spacer(Modifier.height(8.dp))

                ContinueButton(
                    text = "Skip",
                    backgroundColor = backGroundGray,
                    textColor = textColorDark,
                    onClick = {
                        viewModel.updateProgress(true)
                        navigator.push(AddressScreen(viewModel))
                    }
                )

            }
        }
}

@Composable
fun GenderSelectionGroup() {
    val options = listOf("Male", "Female", "Non-binary", "Not Listed")
    var selectedOption by remember { mutableStateOf(options[0]) }

    Column {
        options.forEach { option ->
            GenderOption(
                option = option,
                isSelected = selectedOption == option,
                onOptionSelected = { selectedOption = it }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun GenderOption(
    option: String,
    isSelected: Boolean,
    onOptionSelected: (String) -> Unit
) {
    val borderColor = if (isSelected) colorAccent else Color(0xFFEBEBF0)
    val borderWidth = if (isSelected) 2.dp else 1.dp
    val backgroundColor = if (isSelected) Color(0xFFF7FDF9) else backGroundWhite

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(47.dp)
            .background(backgroundColor)
            .border(BorderStroke(borderWidth, borderColor), RoundedCornerShape(36.dp))
            .clickable { onOptionSelected(option) }
    ) {
        Text(
            text = option,
            style = MaterialTheme.typography.subtitle1
        )
    }
}