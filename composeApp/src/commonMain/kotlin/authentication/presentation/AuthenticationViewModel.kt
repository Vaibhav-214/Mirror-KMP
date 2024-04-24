package authentication.presentation

import authentication.domain.AuthResponse
import authentication.domain.AuthenticationRepo
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.gotrue.SessionStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthenticationViewModel(
    private val authenticationRepo: AuthenticationRepo
) : ViewModel() {

    private val _signInUiState = MutableStateFlow(SignInUiState())
    val signInUiState = _signInUiState.asStateFlow()

    init{
        checkAppleSignInStatus()
    }

    //Trigger to get otp
    fun authenticateWithEmailAndOtp(email: String) = viewModelScope.launch {

        _signInUiState.update {
            it.copy(
                otpSentStatus = AuthResponse.Loading
            )
        }

        _signInUiState.update {
            it.copy(
                otpSentStatus = authenticationRepo.signUpWithEmailAndOtp(email)
            )
        }
    }

    //verify otp
    fun validateOTP(otp: String) = viewModelScope.launch {
        _signInUiState.update {
            it.copy(
                otpValidateStatus = AuthResponse.Loading
            )
        }

        _signInUiState.update {
            it.copy(
                otpValidateStatus = authenticationRepo.verifyEmailWithOtp(otp)
            )
        }
    }

    //check if user already exists in general_user table
    fun checkIfUserExists() = viewModelScope.launch {
        _signInUiState.update {
            it.copy(
                doesUserExist = authenticationRepo.doesUserAlreadyExist()
            )
        }
    }


    //google native one tap sign in
    fun checkGoogleLoginStatus(
        result: NativeSignInResult,
    ) {
        _signInUiState.update {
            it.copy(
                googleSignInStatus = AuthResponse.Loading
            )
        }
        when (result) {
            is NativeSignInResult.Success -> {
                println("successful apple sign in")
                authenticationRepo.saveToken()
                //navigate
                _signInUiState.update {
                    it.copy(
                        googleSignInStatus = AuthResponse.Success("Successful google sign in")
                    )
                }

                viewModelScope.launch {
                    _signInUiState.update {
                        it.copy(
                            doesUserExist = authenticationRepo.doesUserAlreadyExist()
                        )
                    }
                }
            }

            is NativeSignInResult.ClosedByUser -> {
                _signInUiState.update {
                    it.copy(
                        googleSignInStatus = AuthResponse.Failure(Exception("Cancelled"))
                    )
                }
                println("closed by user")
            }

            is NativeSignInResult.Error -> {
                _signInUiState.update {
                    it.copy(
                        googleSignInStatus = AuthResponse.Failure(Exception(result.message))
                    )
                }
                println("Error ${result.message}")
            }

            is NativeSignInResult.NetworkError -> {
                _signInUiState.update {
                    it.copy(
                        googleSignInStatus = AuthResponse.Failure(Exception("Please check your Internet"))
                    )
                }
                println("Network error ${result.message}")
            }
        }
    }


    fun checkAppleSignInStatus() = viewModelScope.launch {
        _signInUiState.update {
            it.copy(
                appleSignInStatus = AuthResponse.Loading
            )
        }
        authenticationRepo.listenToAuthStatus().collect {sessionStatus ->
            when(sessionStatus) {
                is SessionStatus.Authenticated -> {
                    authenticationRepo.saveToken()
                    _signInUiState.update {
                        it.copy(
                            appleSignInStatus = AuthResponse.Success("Apple auth success"),
                            doesUserExist = authenticationRepo.doesUserAlreadyExist()
                        )
                    }
                }
                is SessionStatus.NotAuthenticated -> {
                    println("authenticated falied")
                }
                is SessionStatus.NetworkError -> {
                    println("Network error")
                }
                is SessionStatus.LoadingFromStorage -> {
                    println("kuch to loading from storage")
                }
            }
        }
    }



    //google native sign in fallback
    fun googleOAuthSignIn() = viewModelScope.launch {
        _signInUiState.update {
            it.copy(
                googleSignInStatus = AuthResponse.Loading
            )
        }
        authenticationRepo.googleOAuthSignIn().collect {sessionStatus->
            when(sessionStatus) {
                is SessionStatus.Authenticated -> {
                    authenticationRepo.saveToken()
                    _signInUiState.update {
                        it.copy(
                            googleSignInStatus = AuthResponse.Success("Google OAuth Success"),
                            doesUserExist = authenticationRepo.doesUserAlreadyExist()
                        )
                    }
                }
                is SessionStatus.NotAuthenticated -> {
                    println("Not authenticated")
                }

                is SessionStatus.LoadingFromStorage -> {
                    println("loading from storage")
                }

                is SessionStatus.NetworkError -> {
                    _signInUiState.update {
                        it.copy(
                            googleSignInStatus = AuthResponse.Failure(Exception("Network Error")),
                        )
                    }
                }
            }
        }
    }

}