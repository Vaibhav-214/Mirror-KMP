package authentication.presentation

import authentication.domain.AuthResponse

data class SignInUiState(
    val otpSentStatus: AuthResponse? = null,
    val otpValidateStatus: AuthResponse? = null,
    val doesUserExist: Boolean? = null,
    val googleSignInStatus: AuthResponse? = null,
    val appleSignInStatus: AuthResponse? = null,
)
