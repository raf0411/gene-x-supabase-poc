package kalbe.corp.genexsupabasepoc.ui.screen

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dynamiclayer.components.button.Button
import com.dynamiclayer.components.inputField.InputField
import com.dynamiclayer.components.inputField.util.InputFieldSize
import com.dynamiclayer.components.inputField.util.InputFieldState
import com.dynamiclayer.components.inputField.util.InputFieldType
import kalbe.corp.genexsupabasepoc.R
import kalbe.corp.genexsupabasepoc.data.AuthRepository
import kalbe.corp.genexsupabasepoc.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    authRepository: AuthRepository,
    navController: NavController,
){
    var loginField by remember { mutableStateOf(TextFieldValue("")) }
    var loginFieldType by remember { mutableStateOf(InputFieldType.default) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    fun isEmail(input: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(input).matches()
    }

    fun isPhone(input: String): Boolean {
        val phoneRegex = Regex("^\\+?\\d{10,15}$")
        return phoneRegex.matches(input)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.grey)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            painter = painterResource(id = R.drawable.dna),
            contentDescription = null,
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp))
                .background(color = colorResource(id = R.color.white)),
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Email/Phone", fontSize = 16.sp, fontWeight = FontWeight.Bold)

                Spacer(Modifier.height(16.dp))

                InputField(
                    size = InputFieldSize.lg,
                    onValueChange = {
                        loginField = it
                    },
                    errorText = errorMessage,
                    placeholder = "Enter your email/phone",
                    type = loginFieldType,
                    text = loginField,
                    state = InputFieldState.default,
                )

                Spacer(Modifier.height(16.dp))

                Button(
                    label = "Login",
                    onClick = {
                        coroutineScope.launch {
                            val loginString = loginField.text

                            try {
                                if (isEmail(loginString)) {
                                    Log.d("LoginCheck", "Email detected")
                                    loginFieldType = InputFieldType.success
                                    navController.navigate(Routes.EmailLoginScreen(email = loginString))
                                } else if (isPhone(loginString)) {
                                    Log.d("LoginCheck", "Phone detected: $loginString")
                                    val success = authRepository.sendOtpToPhone(loginString)

                                    if (success) {
                                        Log.d("LoginCheck", "OTP Sent successfully, navigating to OTP screen.")
                                        navController.navigate(Routes.OtpScreen(phone = loginString))
                                    } else {
                                        errorMessage = "Failed to send OTP. Please check the number or try again."
                                        Log.w("LoginCheck", "sendOtpToPhone returned false.")
                                    }

                                } else {
                                    loginFieldType = InputFieldType.error
                                    errorMessage = "Invalid email or phone format."
                                }
                            } catch (e: Exception) {
                                errorMessage = "Login failed: ${e.message}"
                                Log.e("LoginCheck", "Login exception: ${e.message}", e)
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color.Black,
//                        contentColor = Color.White
//                    )
                )

                errorMessage?.let {
                    Toast.makeText(LocalContext.current, it, Toast.LENGTH_LONG).show()
                    errorMessage = null
                }
            }
        }
    }
}