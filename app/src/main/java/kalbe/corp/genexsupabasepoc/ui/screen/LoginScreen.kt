package kalbe.corp.genexsupabasepoc.ui.screen

import android.util.Log
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kalbe.corp.genexsupabasepoc.R
import kalbe.corp.genexsupabasepoc.data.AuthRepository
import kalbe.corp.genexsupabasepoc.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    authRepository: AuthRepository,
    navController: NavController,
){
    var loginField by remember { mutableStateOf("") }

    fun isEmail(input: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()
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
                OutlinedTextField(
                    value = loginField,
                    onValueChange = { loginField = it },
                    label = { Text("Enter your email/phone") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(32.dp),
                )

                Spacer(Modifier.height(16.dp))

                val coroutineScope = rememberCoroutineScope()
                var errorMessage by remember { mutableStateOf<String?>(null) }

                Button(
                    onClick = {
                        coroutineScope.launch {
                            try {
                                if (isEmail(loginField)) {
                                    Log.d("LoginCheck", "Email detected")
                                    navController.navigate(Routes.EmailLoginScreen(email = loginField))
                                } else if (isPhone(loginField)) {
                                    Log.d("LoginCheck", "Phone detected: $loginField")
                                    val success = authRepository.sendOtpToPhone(loginField)

                                    if (success) {
                                        Log.d("LoginCheck", "OTP Sent successfully, navigating to OTP screen.")
                                        navController.navigate(Routes.OtpScreen(phone = loginField))
                                    } else {
                                        errorMessage = "Failed to send OTP. Please check the number or try again."
                                        Log.w("LoginCheck", "sendOtpToPhone returned false.")
                                    }

                                } else {
                                    errorMessage = "Invalid email or phone format."
                                }
                            } catch (e: Exception) {
                                errorMessage = "Login failed: ${e.message}"
                                Log.e("LoginCheck", "Login exception: ${e.message}", e)
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text("Login")
                }
                errorMessage?.let {
                    Toast.makeText(LocalContext.current, it, Toast.LENGTH_LONG).show()
                    errorMessage = null
                }
            }
        }
    }
}