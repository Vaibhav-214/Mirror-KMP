package screens.auth

import authentication.domain.AuthResponse
import authentication.domain.AuthenticationRepo
import authentication.domain.AccountType
import authentication.domain.GeneralUser
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authenticationRepo: AuthenticationRepo
): ViewModel() {

    private val _uiState = MutableStateFlow<AuthResponse?>(null)
    val userState = _uiState.asStateFlow()

    private val _loginState = MutableStateFlow<Boolean?>(null)
    val loginState = _loginState.asStateFlow()

    private val _userExists = MutableStateFlow<Boolean?>(null)
    val userExists = _userExists.asStateFlow()


    fun updateUserState(state: AuthResponse) {
        _uiState.value = state
    }

    fun updateUserExistsOnIos() {
        _userExists.value = true
        _loginState.value = true
        _uiState.value = AuthResponse.Success("dummy user on ios ")
    }


    fun isUserLoggedIn() = viewModelScope.launch {
        _loginState.value = authenticationRepo.isUserLoggedIn()
        _userExists.value = _loginState.value
    }

    fun createGeneralUser() = viewModelScope.launch {
        val user = GeneralUser(
            userName = "Vaibhav Patil",
            email = "patil.vaibhav2147.vp@gmail.com",
            accountType = AccountType.GENERAL
        )

        val result = authenticationRepo.createNewUser(user)

        println(result)
    }

    fun checkGoogleLoginStatus(
        result: NativeSignInResult,
        ) {
        _uiState.value = AuthResponse.Loading

        when(result) {
            is NativeSignInResult.Success -> {
                authenticationRepo.saveToken()
                //navigate
                _uiState.value = AuthResponse.Success("Sign In Successful")
                println("success apple sign in")

                viewModelScope.launch {
                    _userExists.value = authenticationRepo.doesUserAlreadyExist()
                }
            }
            is NativeSignInResult.ClosedByUser -> {
                _uiState.value = AuthResponse.Failure(Exception("Cancelled"))
                println("closed by user")
            }

            is NativeSignInResult.Error -> {
                _uiState.value = AuthResponse.Failure(Exception(result.message))
                println("Error ${result.message}")
            }

            is NativeSignInResult.NetworkError -> {
                _uiState.value = AuthResponse.Failure(Exception("Please check your internet"))
                println("Network error ${result.message}")
            }
        }
    }
}
