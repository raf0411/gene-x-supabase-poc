package kalbe.corp.genexsupabasepoc.viewModel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kalbe.corp.genexsupabasepoc.data.model.UserState
import kalbe.corp.genexsupabasepoc.data.network.supabaseClient
import kalbe.corp.genexsupabasepoc.utils.SecurePrefs
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {
    private val _userState = mutableStateOf<UserState>(UserState.Loading)

    fun loginUser(context: Context, email: String, password: String) {
        viewModelScope.launch {
            try {
                supabaseClient.auth.signInWith(Email){
                    this.email = email
                    this.password = password
                }

                saveToken(context)
                _userState.value = UserState.Success("User login Successful!")

            } catch (e: Exception) {
                _userState.value = UserState.Error(("Error: ${e.message}"))
            }
        }
    }

    fun logout(context: Context){
        val securePrefs = SecurePrefs(context)

        viewModelScope.launch {
            try {
                supabaseClient.auth.signOut()
                securePrefs.clear()

                _userState.value = UserState.Success("Logged out Successful!")
            }catch (e: Exception){
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }

    private fun saveToken(context: Context){
        val securePrefs = SecurePrefs(context)

        viewModelScope.launch {
            val accessToken = supabaseClient.auth.currentAccessTokenOrNull()
            securePrefs.putEncrypted("access_token", accessToken.toString())
        }
    }

    private fun getToken(context: Context): String{
        val securePrefs = SecurePrefs(context)
        val accessToken = securePrefs.getDecrypted("access_token").toString()

        return accessToken
    }

    fun isUserLoggedIn(
        context: Context
    ) {
        viewModelScope.launch {
            try {
                val token = getToken(context)

                if (token.isEmpty()) {
                    _userState.value = UserState. Error("User is not logged in!")
                } else {
                    supabaseClient.auth.retrieveUser(token)
                    supabaseClient.auth.refreshCurrentSession()
                    saveToken (context)
                    _userState.value = UserState. Success("User is already logged in!")
                }
            } catch (e: Exception) {
                _userState.value = UserState. Error("Error: ${e.message}")
            }
        }
    }
}