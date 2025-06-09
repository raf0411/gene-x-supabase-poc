package kalbe.corp.genexsupabasepoc.ui.screen

import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import kalbe.corp.genexsupabasepoc.navigation.Routes
import kalbe.corp.genexsupabasepoc.viewModel.MfaSetupViewModel

@Composable
fun QrCodeImage(
    content: String,
    modifier: Modifier = Modifier,
) {
    val imageBitmap = remember(content) {
        try {
            val writer = QRCodeWriter()
            val hints = mapOf(EncodeHintType.MARGIN to 0)
            val bitMatrix = writer.encode(
                content,
                BarcodeFormat.QR_CODE,
                512,
                512,
                hints
            )
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.Black.toArgb() else Color.White.toArgb())
                }
            }
            bitmap.asImageBitmap()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    if (imageBitmap != null) {
        Image(
            bitmap = imageBitmap,
            contentDescription = "MFA QR Code",
            modifier = modifier
        )
    } else {
        Box(modifier = modifier.background(Color.Gray))
    }
}

@Composable
fun MfaSetupScreen(
    navController: NavController,
    mfaSetupViewModel: MfaSetupViewModel
) {
    val state by mfaSetupViewModel.enrollmentState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        mfaSetupViewModel.checkCurrentMfaStatus()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (state.isAlreadyEnabled) {
            true -> {
                Text("✅ 2FA is Active", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                LaunchedEffect(Unit) {
                    navController.navigate(Routes.DashboardScreen)
                }
            }
            false -> {
                when {
                    state.isVerified -> {
                        Text("✅ Success!", fontSize = 24.sp, fontWeight = FontWeight.Bold)

                        LaunchedEffect(Unit) {
                            Toast.makeText(context, "MFA Already Enabled!", Toast.LENGTH_SHORT).show()
                            navController.navigate(Routes.DashboardScreen)
                        }
                    }

                    !state.otpUri.isNullOrEmpty() -> {
                        Text("Scan QR Code", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(8.dp))
                        Text("Use your authenticator app to scan this code.", textAlign = TextAlign.Center)
                        Spacer(Modifier.height(16.dp))

                        Log.d("CheckURI", state.otpUri!!)

                        QrCodeImage(
                            content = state.otpUri!!,
                            modifier = Modifier.size(220.dp)
                        )

                        Spacer(Modifier.height(16.dp))
                        OutlinedTextField(
                            value = state.userCode,
                            onValueChange = { mfaSetupViewModel.onCodeChanged(it) },
                            label = { Text("6-Digit Code") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(Modifier.height(16.dp))
                        Button(
                            onClick = { mfaSetupViewModel.verifyAndEnableMfa() },
                            enabled = !state.isLoading && state.userCode.length == 6
                        ) {
                            Text("Verify and Enable")
                        }
                    }

                    else -> {
                        Text("Enable 2FA", fontSize = 24.sp, fontWeight = FontWeight.Bold)

                        Spacer(Modifier.height(32.dp))

                        Button(
                            onClick = { mfaSetupViewModel.startTotpEnrollment() },
                            enabled = !state.isLoading
                        ) {
                            Text("Generate QR Code")
                        }
                    }
                }
            }
            null -> {
                CircularProgressIndicator()
                Spacer(Modifier.height(16.dp))
                Text("Checking security status...")
            }
        }

        if (state.isLoading) {
            Spacer(Modifier.height(16.dp))
            CircularProgressIndicator()
        }
        state.error?.let {
            Spacer(Modifier.height(16.dp))
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )
        }
    }
}