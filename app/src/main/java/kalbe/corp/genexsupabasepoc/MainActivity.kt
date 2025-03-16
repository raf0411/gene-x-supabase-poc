package kalbe.corp.genexsupabasepoc

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.navigation.NavController
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.IDToken
import kalbe.corp.genexsupabasepoc.data.supabaseClient
import kalbe.corp.genexsupabasepoc.navigation.NavGraph
import kalbe.corp.genexsupabasepoc.navigation.Routes
import kalbe.corp.genexsupabasepoc.ui.theme.GeneXSupabasePOCTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.security.MessageDigest
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeneXSupabasePOCTheme {
                MyApp(supabaseClient)
            }
        }
    }
}

@Composable
fun MyApp(supabaseClient: SupabaseClient) {
    NavGraph(supabaseClient = supabaseClient)
}

@Composable
fun RegisterScreen(
    navController: NavController
) {
    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }

    val context: Context = LocalContext.current
    val authManager = remember{AuthManager(context)}
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.TopCenter
    ) {
        Gradient()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 110.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Create an Account", fontWeight = FontWeight.Bold, color = Color.White)

            Spacer(Modifier.height(20.dp))

            GoogleButton(onClick = {
                try {
                    authManager.loginGoogle()
                        .onEach { result ->
                            if(result is AuthResponse.Success){
                                Log.d("auth", "Google Success!")

                                navController.navigate(
                                    Routes.ProductCatalogueScreen
                                )
                            } else {
                                Log.e("auth", "Google Failed! $result")
                            }
                        }
                        .launchIn(coroutineScope)
                } catch (e: Exception){
                    Log.e("auth", "Google Auth Error!", e)
                }
            })

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(30.dp),
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(Color.White.copy(alpha = .2f))
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(Color.White.copy(alpha = .2f))
                )
            }

//            Column(
//                horizontalAlignment = Alignment.Start,
//            ) {
//                Text(
//                    text = "Email",
//                    color = Color.White,
//                    fontWeight = FontWeight.Bold,
//                )
//
//                Spacer(Modifier.height(4.dp))
//
//                TextField(
//                    value = emailValue,
//                    onValueChange = { newValue ->
//                        emailValue = newValue
//                    },
//                    placeholder = { Text(
//                        "user@email.com",
//                        color = Color.Black.copy(alpha = 0.5f),
//                    ) },
//                    shape = RoundedCornerShape(10.dp),
//                    colors = TextFieldDefaults.colors(
//                        unfocusedIndicatorColor = Color.Transparent,
//                        focusedIndicatorColor = Color.Transparent,
//                        focusedContainerColor = Color.LightGray,
//                        unfocusedContainerColor = Color.LightGray,
//                    ),
//                    modifier = Modifier.fillMaxWidth(),
//                )
//            }
//
//            Spacer(Modifier.height(20.dp))
//
//            Column(
//                horizontalAlignment = Alignment.Start,
//            ) {
//                Text(
//                    text = "Password",
//                    color = Color.White,
//                    fontWeight = FontWeight.Bold,
//                )
//
//                Spacer(Modifier.height(4.dp))
//
//                TextField(
//                    value = passwordValue,
//                    onValueChange = { newValue ->
//                        passwordValue = newValue
//                    },
//                    placeholder = { Text(
//                        "Enter your password",
//                        color = Color.Black.copy(alpha = 0.5f),
//                    ) },
//                    visualTransformation = PasswordVisualTransformation(),
//                    shape = RoundedCornerShape(10.dp),
//                    colors = TextFieldDefaults.colors(
//                        unfocusedIndicatorColor = Color.Transparent,
//                        focusedIndicatorColor = Color.Transparent,
//                        focusedContainerColor = Color.LightGray,
//                        unfocusedContainerColor = Color.LightGray,
//                    ),
//                    modifier = Modifier.fillMaxWidth(),
//                )
//            }
//
//            Spacer(Modifier.height(25.dp))
//
//            Button (
//                onClick = {
//                    authManager.signUpWithEmail(emailValue, passwordValue)
//                        .onEach { result ->
//                            if(result is AuthResponse.Success){
//                                Log.d("auth", "Email Success!")
//                            } else {
//                                Log.e("auth", "Email Failed!")
//                            }
//                        }
//                        .launchIn(coroutineScope)
//                },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color.White,
//                ),
//                shape = RoundedCornerShape(10.dp),
//                modifier = Modifier.fillMaxWidth(),
//            ){
//                Text("Sign up", color = Color.Black, modifier = Modifier.padding(vertical = 4.dp))
//            }
//
//            Spacer(modifier = Modifier.height(25.dp))
//
//            TextButton(
//                onClick = {
//
//                }
//            ) {
//                Text(
//                    text = buildAnnotatedString {
//                        withStyle(style = SpanStyle(
//                            fontWeight = FontWeight.Light,
//                            color = Color.White.copy(alpha = .8f),
//                        )){
//                            append("Already have an account? ")
//                        }
//
//                        withStyle(style = SpanStyle(
//                            fontWeight = FontWeight.Bold,
//                            color = Color.White,
//                        )
//                        ){
//                            append("Login")
//                        }
//                    }
//                 )
//            }
        }
    }
}

@Composable
private fun GoogleButton(onClick: () -> Unit) {
    OutlinedButton(
        onClick = { onClick() },
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "Sign In With Google",
            modifier = Modifier.padding(vertical = 4.dp),
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun Gradient() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.DarkGray)
    )
}

// AUTHENTICATION CODE
sealed interface AuthResponse {
    data object Success : AuthResponse
    data class Error(val message: String?) : AuthResponse
}

class AuthManager(
    private val context: Context
) {
    fun signUpWithEmail(emailValue: String, passwordValue: String): Flow<AuthResponse> = flow {
        try {
            supabaseClient.auth.signUpWith(Email) {
                email = emailValue
                password = passwordValue
            }
            emit(AuthResponse.Success)
        } catch (e: Exception) {
            emit(AuthResponse.Error(e.localizedMessage))
        }
    }

    fun signInWithEmail(emailValue: String, passwordValue: String): Flow<AuthResponse> = flow {
        try {
            supabaseClient.auth.signInWith(Email) {
                email = emailValue
                password = passwordValue
            }

            emit(AuthResponse.Success)
        } catch (e: Exception) {
            emit(AuthResponse.Error(e.localizedMessage))
        }
    }

    fun createNonce(): String {
        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)

        return digest.fold("") { str, it ->
            str + "%02x".format(it)
        }
    }

    fun loginGoogle(): Flow<AuthResponse> = flow {
        val hashedNonce = createNonce()
        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId("452887781581-m9n1itk8n6a4dvefsuajadrshi3g245u.apps.googleusercontent.com")
            .setNonce(hashedNonce).setAutoSelectEnabled(false).setFilterByAuthorizedAccounts(false)
            .build()
        val req = GetCredentialRequest.Builder().addCredentialOption(googleIdOption).build()
        val credentialManager = CredentialManager.create(context)

        try {
            val res = credentialManager.getCredential(
                context = context,
                request = req,
            )

            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(res.credential.data)

            val googleIdToken = googleIdTokenCredential.idToken

            supabaseClient.auth.signInWith(IDToken) {
                idToken = googleIdToken
                provider = Google
            }

            emit(AuthResponse.Success)
        } catch (e: Exception) {
            Log.e("auth", "Error Login Google", e)
            emit(AuthResponse.Error(e.localizedMessage))
        }
    }
}