package kalbe.corp.genexsupabasepoc.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kalbe.corp.genexsupabasepoc.data.AuthRepository
import kalbe.corp.genexsupabasepoc.navigation.Routes
import kalbe.corp.genexsupabasepoc.ui.components.OtpInput
import kotlinx.coroutines.launch

@Composable
fun OtpScreen(
    authRepository: AuthRepository,
    navController: NavController,
    phone: String,
) {
    val coroutineScope = rememberCoroutineScope()
    var otpCode by remember { mutableStateOf("") }
    var isVerifying by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    LaunchedEffect(phone) {
        Log.d("OtpScreen", "Received phone number for verification: $phone")
        if (!phone.startsWith("+")) {
            Log.w("OtpScreen", "Warning: Received phone number does not start with '+'. Verification might fail if E.164 format is required.")
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Enter OTP Code", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Sent to $phone", fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(24.dp))

        OtpInput(onOtpComplete = { otp ->
            Log.d("OtpScreen", "OTP input complete: $otp")
            otpCode = otp
        })

        Spacer(modifier = Modifier.height(16.dp))

        errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }


        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                errorMessage = null
                if (otpCode.length != 6) {
                    errorMessage = "Please enter the complete 6-digit OTP."
                    return@Button
                }

                isVerifying = true
                coroutineScope.launch {
                    try {
                        val success = authRepository.verifyOtp(otp = otpCode, phone = phone)

                        if (success) {
                            Log.d("OtpScreen", "OTP verified successfully. Navigating to Profile.")
                            Toast.makeText(context, "Verification Successful!", Toast.LENGTH_SHORT).show()
                            navController.navigate(Routes.DashboardScreen) {
                                popUpTo(navController.graph.startDestinationId) { inclusive = true }
                            }
                        } else {
                            errorMessage = "Invalid or expired OTP. Please try again."
                            Log.w("OtpScreen", "OTP verification failed (returned false).")
                        }
                    } catch (e: Exception) {
                        errorMessage = "Verification failed: ${e.message ?: "Unknown error"}"
                        Log.e("OtpScreen", "OTP verification exception: ${e.message}", e)
                    } finally {
                        isVerifying = false
                    }
                }
            },
            enabled = !isVerifying && otpCode.length == 6,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isVerifying) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            } else {
                Text(text = "Verify OTP", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}