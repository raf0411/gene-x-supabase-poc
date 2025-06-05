package kalbe.corp.genexsupabasepoc.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kalbe.corp.genexsupabasepoc.data.network.supabaseClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MfaEnrollmentState(
    val qrCodeSvg: String? = null,
    val factorId: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class MfaSetupViewModel: ViewModel() {
    private val _enrollmentState = MutableStateFlow(MfaEnrollmentState())
    val enrollmentState: StateFlow<MfaEnrollmentState> = _enrollmentState.asStateFlow()

    private val supabase = supabaseClient

    fun startTotpEnrollment(friendlyName: String = "My App TOTP") {
        viewModelScope.launch {
            _enrollmentState.value = MfaEnrollmentState(isLoading = true)
            try {
//                // Attempt this declaration
//                val factor: MfaFactor<TotpDetails> = supabase.auth.mfa.enroll(
//                    factorType = FactorType.TOTP,
//                    friendlyName = friendlyName
//                )
//
//                // If the above line compiles, then try to access the qrCode.
//                // Most likely, it's still via the .totp property:
//                val qrCodeString: String? = factor.totp?.qrCode
//                val factorIdForChallenge = factor.id
//
//                if (qrCodeString != null && factorIdForChallenge != null) {
//                    _enrollmentState.value = MfaEnrollmentState(
//                        qrCodeSvg = qrCodeString,
//                        factorId = factorIdForChallenge,
//                        isLoading = false
//                    )
//                } else {
//                    _enrollmentState.value = MfaEnrollmentState(error = "Failed to retrieve TOTP QR code or Factor ID.", isLoading = false)
//                }

            } catch (e: Exception) {
                _enrollmentState.value = MfaEnrollmentState(error = e.message ?: "Unknown error occurred", isLoading = false)
                // Log.e("MfaSetupViewModel", "Error enrolling TOTP", e)
            }
        }
    }

    // You'll need another function to create the challenge and then verify it.
    // This is the next step after showing the QR code and the user entering the TOTP.
    // fun createChallengeAndVerify(factorId: String, code: String) { ... }
}