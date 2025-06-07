package kalbe.corp.genexsupabasepoc.ui.screen

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kalbe.corp.genexsupabasepoc.viewModel.MfaSetupViewModel
import kotlinx.coroutines.launch

@Composable
fun MfaSetupScreen(
    navController: NavController,
    mfaSetupViewModel: MfaSetupViewModel
) {
    val state by mfaSetupViewModel.enrollmentState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            mfaSetupViewModel.checkCurrentMfaStatus()
        }
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
                // ... etc.
            }
            false -> {
                when {
                    state.isVerified -> {
                        Text("✅ Success!", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        // ... etc.
                    }

                    state.qrCodeSvg != null -> {
                        Text("Scan QR Code", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(8.dp))
                        Text("Use your authenticator app to scan this code.", textAlign = TextAlign.Center)
                        Spacer(Modifier.height(16.dp))

                        AsyncImage(
                            model = state.qrCodeSvg,
                            contentDescription = "MFA QR Code",
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
                            onClick = {
                                coroutineScope.launch {
                                    mfaSetupViewModel.verifyAndEnableMfa()
                                }
                            },
                            enabled = !state.isLoading && state.userCode.length == 6
                        ) {
                            Text("Verify and Enable")
                        }
                    }

                    else -> {
                        Text("Enable 2FA", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    mfaSetupViewModel.startTotpEnrollment()
                                }
                            },
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