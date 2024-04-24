package authentication.domain

sealed class AuthResponse {
    //DOUBT should there be anything else in this sealed class
    object Loading: AuthResponse()
    data class Success(val message: String): AuthResponse()
    data class Failure(val e: Exception): AuthResponse()
}
