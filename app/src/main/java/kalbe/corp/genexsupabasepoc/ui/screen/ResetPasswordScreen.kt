package kalbe.corp.genexsupabasepoc.ui.screen

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

@Composable
fun ResetPasswordScreen(
    supabaseClient: SupabaseClient,
    onResetSuccess: () -> Unit
) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Text("Reset Password", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

//        Text(
//            text = "Please enter your email and we will send an OTP code in the next step to reset your password",
//            fontSize = 14.sp,
//            fontWeight = FontWeight.Normal,
//        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = { Text("New Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(32.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(32.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    if (newPassword != confirmPassword) {
                        errorMessage = "Passwords do not match"
                        return@launch
                    }

                    try {
                        val user = supabaseClient.auth.currentUserOrNull()
                            ?: throw Exception("User not logged in")

                        supabaseClient.auth.updateUser {
                            password = newPassword
                        }

                        supabaseClient.from("auth").update({
                            set("is_first_time_login", false)
                        }) {
                            filter {
                                eq("user_id", user.id)
                            }
                        }

                        onResetSuccess()

                    } catch (e: Exception) {
                        errorMessage = "Failed to reset password: ${e.message}"
                    }

                    Log.e("ErrorMessage", errorMessage.toString())
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
        ) {
            Text("Reset Password")
        }

        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG)
    }
}
