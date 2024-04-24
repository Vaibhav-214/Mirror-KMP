package authentication.data

import authentication.domain.AuthResponse
import authentication.domain.AuthenticationRepo
import authentication.domain.GeneralUser
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.exceptions.HttpRequestException
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.exceptions.UnauthorizedRestException
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.OTP
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Count
import io.ktor.client.plugins.HttpRequestTimeoutException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import util.Constants

class AuthenticationRepoImpl(
    private val supabaseClient: SupabaseClient,
    private val settings: Settings
): AuthenticationRepo {

    override suspend fun signUpWithEmailAndOtp(email: String): AuthResponse {
        return  try {

            //storing email for use in otp validation, user profile creation
            settings[Constants.USER_EMAIL] = email

            //DOUBT same api is called for signing up with email as well as login with email
            // and these methods don't return anything so we don't know if the user already
            // exists in case of sign up
            // update 1.We dont care if user exists or not in auth table , check user table later
            supabaseClient.auth.signUpWith(OTP) {
                this.email = email
                this.createUser = true
            }
            AuthResponse.Success("Otp sent")

        }catch (e: RestException) {
            //Some unknown exception - response related
            AuthResponse.Failure(e)
            //DOUBT should we forward exception object or a message to the next layer
        }catch (e: HttpRequestTimeoutException) {
            //timeout issue
            AuthResponse.Failure(e)
        }catch (e: HttpRequestException) {
            //network issue
            AuthResponse.Failure(e)
        }catch (e: Exception) {
            AuthResponse.Failure(e)
        }
    }

    override suspend fun verifyEmailWithOtp(otp: String): AuthResponse {
        return try {
            val email = settings.getStringOrNull(Constants.USER_EMAIL)

            email?.let {
                supabaseClient.auth.verifyEmailOtp(
                    type = OtpType.Email.SIGNUP,
                    email = it,
                    token = otp
                )
                AuthResponse.Success("Email Verified")

            } ?: AuthResponse.Failure(Exception("Null email fetched from (datastore)settings"))


        }catch (e: UnauthorizedRestException) {
            //TODO : Due to this issue this is a temporary fix to handle the situation
            // issue - https://github.com/supabase-community/supabase-kt/issues/450
            //One of the reasons - Exception thrown when user doesn't exist
            verifyOtpIfUserAlreadyExists(otp)
        }catch (e: RestException) {
            //when wrong otp is entered(Need to confirm)
            AuthResponse.Failure(e)
        }catch (e: HttpRequestTimeoutException) {
            AuthResponse.Failure(e)
        }catch (e: HttpRequestException) {
            AuthResponse.Failure(e)
        }catch (e: Exception) {
            AuthResponse.Failure(e)
        }
    }

    override suspend fun loginUserWithEmailAndOtp(email: String, otp: String): AuthResponse {
        return try {
            //here create user is false which means if no user exists already then RestException
            //will be thrown
            supabaseClient.auth.signInWith(OTP) {
                this.email = email
            }
            AuthResponse.Success("Otp sent")
        }catch (e: RestException) {
            //One of the reasons - Exception thrown when user doesn't exist

            verifyOtpIfUserAlreadyExists(otp)
        }catch (e: HttpRequestTimeoutException) {
            AuthResponse.Failure(e)
        }catch (e: HttpRequestException) {
            AuthResponse.Failure(e)
        }catch (e: Exception) {
            AuthResponse.Failure(e)
        }
    }

    override suspend fun googleOAuthSignIn(): StateFlow<SessionStatus>{
            supabaseClient.auth.signUpWith(Google)

           return supabaseClient.auth.sessionStatus
    }

    override fun saveToken(): Boolean {
        //DOUBT
        // 1.Should I keep try catch here?
        // 2.AM I breaking Single responsibility principle
        return try {
            val token: String? =  supabaseClient.auth.currentAccessTokenOrNull()
            settings[Constants.TOKEN] = token
            true
        }catch (e: Exception) {
            false
        }
    }

    override fun getToken(): String? {
        val token: String? = settings.getStringOrNull(Constants.TOKEN)
        return token
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return try {
            val token = getToken()
            if (token.isNullOrEmpty()) {
                false
            }else {
                supabaseClient.auth.retrieveUser(token)
                supabaseClient.auth.refreshCurrentSession()
                saveToken()
                true
            }
        }catch (e: RestException) {
            println("excception $e")
            false
        }

    }

    override suspend fun createNewUser(user: GeneralUser): AuthResponse {
        return try {
            val result = supabaseClient.postgrest[Constants.GENERAL_USER_TABLE]
                .insert(user)

            AuthResponse.Success(result.data)

        }catch (e: Exception) {
            //TODO Specify exceptions
            AuthResponse.Failure(e)
        }
    }

    override suspend fun doesUserAlreadyExist(): Boolean { 
        try {
            val jwtToken = getToken()
            val user = supabaseClient.auth.retrieveUser(jwtToken ?:"")
            val email = user.email
            println("email to be searched - $email")
            val result = supabaseClient.postgrest[Constants.GENERAL_USER_TABLE]
                .select (columns = Columns.list("email")){//DOUBT learn about count alogorithms in postgrest
                    filter {
                        GeneralUser::email eq email
                    }
                }.countOrNull()

            println("user exist query result : $result")


            return result == 1L


        }catch (e: Exception) {
            println("exception : $e")
            return false
        }
    }

    suspend fun verifyOtpIfUserAlreadyExists(otp: String):AuthResponse {
        return try {
            val email = settings.getStringOrNull(Constants.USER_EMAIL)

            email?.let {
                supabaseClient.auth.verifyEmailOtp(
                    type = OtpType.Email.MAGIC_LINK,
                    email = it,
                    token = otp
                )
                AuthResponse.Success("Email Verified")

            } ?: AuthResponse.Failure(Exception("Null email fetched from (datastore)settings"))
        }catch (e: RestException) {
            //when wrong otp is entered(Need to confirm)
            AuthResponse.Failure(e)
        }catch (e: HttpRequestTimeoutException) {
            AuthResponse.Failure(e)
        }catch (e: HttpRequestException) {
            AuthResponse.Failure(e)
        }catch (e: Exception) {
            AuthResponse.Failure(e)
        }
    }

    override fun listenToAuthStatus(): Flow<SessionStatus>   {
//        supabaseClient.auth.sessionStatus.collect {sessionStatus ->
//                val response = when(sessionStatus) {
//                    is SessionStatus.Authenticated -> AuthResponse.Success("congratulations bhai")
//                    is SessionStatus.NotAuthenticated -> AuthResponse.Failure(Exception("Not authenticated"))
//                    is SessionStatus.NetworkError -> AuthResponse.Failure(Exception("Network error"))
//                    is SessionStatus.LoadingFromStorage -> AuthResponse.Loading
//                }


//        }
        return supabaseClient.auth.sessionStatus
    }


}