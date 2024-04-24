package authentication.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import authentication.domain.AuthResponse
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import onboarding.presentation.components.ClickableTextWithColor
import onboarding.presentation.components.ContinueButton
import onboarding.presentation.components.OnBoardingTopAppBar
import onboarding.presentation.components.OnboardingTextField
import onboarding.presentation.journey.MainNavigator
import onboarding.util.isValidEmail
import org.koin.compose.koinInject
import platform.NativeSignInButton
import presentation.theme.backGroundGray
import presentation.theme.colorAccent
import presentation.theme.textColorDark
import presentation.theme.textColorSemiDark
import presentation.theme.white

class SignInScreen (
   val appleSignInButton: @Composable () -> Unit
): Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow



        val viewModel: AuthenticationViewModel = koinInject()

        val uiState by remember { viewModel.signInUiState }.collectAsState()


        var email by rememberSaveable { mutableStateOf("") }

        val supabaseAuth = koinInject<SupabaseClient>().composeAuth


        val action = supabaseAuth.rememberSignInWithGoogle(
            onResult = { nativeSignInResult ->
                println("trying apple sign in ")
                viewModel.checkGoogleLoginStatus(nativeSignInResult)
            },
            fallback = {
                println("google fallback")
                viewModel.googleOAuthSignIn()
            }
        )





        uiState.doesUserExist?.let {exists->
            if (exists) {
                navigator.push(MainNavigator())
                println("Go to home screen from login screen")
            } else {
                navigator.push(MainNavigator())
            }
        }

        uiState.appleSignInStatus.let {
            println("authResponse - $it")
        }

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.systemBars),
            bottomBar = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ClickableTextWithColor(
                        nonClickableText = "I am new here. ",
                        clickableText = "Sign Up",
                        nonClickableTextColor = textColorSemiDark,
                        clickableTextColor = colorAccent,
                        fontSize = 16,
                        fontStyle = MaterialTheme.typography.subtitle1,
                        onTextClick = {}
                    )
                }
            }
        ) { padding ->

            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OnBoardingTopAppBar(
                    navigationIconResource = "",
                    title = "Sign In",
                    topPadding = 24,
                    backClick = {}
                )

                Spacer(Modifier.height(32.dp))

                Text(
                    text = "So we can save your\njournal and help you grow!",
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(32.dp))

                OnboardingTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeHolderText = "Email Address",
                    keyboardType = KeyboardType.Email
                )

                Spacer(Modifier.height(26.dp))

                when(val result = uiState.otpSentStatus) {
                    is AuthResponse.Loading -> { CircularProgressIndicator(color = colorAccent) }
                    is AuthResponse.Success -> { navigator.push(OtpScreen(viewModel)) }
                    is AuthResponse.Failure -> { Text("Failure ${result.e}") }
                    null -> {
                        ContinueButton(
                            text = "Continue",
                            backgroundColor = colorAccent,
                            textColor = white,
                            enabled = isValidEmail(email),
                            onClick = {
                                viewModel.authenticateWithEmailAndOtp(email)
                            }
                        )
                    }
                }

                Spacer(Modifier.height(15.dp))

                ClickableTextWithColor(
                    nonClickableText = "by tapping Continue, I accept Mirror’s ",
                    clickableText = "Terms of Use",
                    nonClickableTextColor = Color(0xFF8F90A6),
                    clickableTextColor = Color(0xFF2072EF),
                    fontSize = 10,
                    fontStyle = MaterialTheme.typography.caption,
                    onTextClick = {}
                )


                Spacer(Modifier.height(16.dp))

                Text(
                    text = "OR",
                    style = MaterialTheme.typography.subtitle1
                )

                Spacer(Modifier.height(16.dp))

                ContinueButton(
                    text = "Continue with Google",
                    backgroundColor = backGroundGray,
                    textColor = textColorDark,
                    onClick = {
//                        action.startFlow()
                    }
                )


                Spacer(Modifier.height(8.dp))

                NativeSignInButton()
            }

        }
    }

}