package authentication.domain

import authentication.domain.AuthResponse
import authentication.domain.GeneralUser
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.compose.auth.ui.AuthState
import io.github.jan.supabase.gotrue.SessionStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface AuthenticationRepo {

    suspend fun signUpWithEmailAndOtp(email: String): AuthResponse

    suspend fun verifyEmailWithOtp(otp: String): AuthResponse

    suspend fun loginUserWithEmailAndOtp(email: String, otp: String): AuthResponse

    suspend fun googleOAuthSignIn(): StateFlow<SessionStatus>


    //DOUBT
    // 1.Where Should I write the method to save the session token and to get the session token
    // 2.What should be return type of this method
    // 3.They are not done on background thread it seems, as they are not suspendable so curious
    fun saveToken(): Boolean

    fun getToken(): String?

    suspend fun isUserLoggedIn(): Boolean

    suspend fun createNewUser(user: GeneralUser): AuthResponse

    suspend fun doesUserAlreadyExist(): Boolean

    fun listenToAuthStatus() : Flow<SessionStatus>



}