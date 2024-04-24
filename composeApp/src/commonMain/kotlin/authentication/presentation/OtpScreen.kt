package authentication.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import authentication.domain.AuthResponse
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import onboarding.presentation.components.ContinueButton
import onboarding.presentation.components.OtpTextField
import onboarding.presentation.journey.MainNavigator

import presentation.theme.backGroundGray
import presentation.theme.backGroundWhite
import presentation.theme.colorAccent
import presentation.theme.textColorDark
import presentation.theme.white
import screens.home.HomeScreenContainer


class OtpScreen(private val viewModel: AuthenticationViewModel): Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val uiState by remember { viewModel.signInUiState }.collectAsState()

        var otp by remember { mutableStateOf("") }



        uiState.doesUserExist?.let { exists->
            if (exists) {
                println("navigate to home screen")
            } else {
                println(navigator.push(MainNavigator()))
            }
        }
        Scaffold (
            modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
            bottomBar = {
                Column (
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .windowInsetsPadding(WindowInsets.ime),
                ) {
                    when(val result = uiState.otpValidateStatus) {
                        is AuthResponse.Loading -> { CircularProgressIndicator() }
                        is AuthResponse.Success -> { viewModel.checkIfUserExists()}
                        is AuthResponse.Failure -> { Text("Failure ${result.e}") }
                        null -> {
                            ContinueButton(
                                text = "Continue",
                                backgroundColor = colorAccent,
                                textColor = white,
                                enabled = otp.length == 6,
                                onClick = { viewModel.validateOTP(otp) }
                            )
                        }
                    }

                    Spacer(Modifier.height(8.dp))

                    ContinueButton(
                        text = "Resend OTP",
                        backgroundColor = backGroundGray,
                        textColor = textColorDark,
                        onClick = {}
                    )
                }
            }
        ){paddingValues ->
            Column(
                modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(Modifier.height(80.dp))

                Text(
                    text = "Enter OTP",
                    style = MaterialTheme.typography.h2
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = "Check your mail, we've sent you\nsomething",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(26.dp))

                OtpTextField(
                    otpText = otp,
                    onOtpTextChange = {value, otpFieldFilled ->
                        otp = value
                    }
                )

            }

        }
    }
}