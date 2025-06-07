package kalbe.corp.genexsupabasepoc.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.mfa.AuthenticatorAssuranceLevel
import io.github.jan.supabase.auth.mfa.FactorType
import kalbe.corp.genexsupabasepoc.data.network.supabaseClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MfaEnrollmentState(
    val qrCodeSvg: String? = null,
    val factorId: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val userCode: String = "",
    val isVerified: Boolean = false,
    val isAlreadyEnabled: Boolean? = null
)

class MfaSetupViewModel : ViewModel() {

    private val _enrollmentState = MutableStateFlow(MfaEnrollmentState())
    val enrollmentState: StateFlow<MfaEnrollmentState> = _enrollmentState.asStateFlow()

    private val supabase = supabaseClient

    // This function for updating text field state is fine as is
    fun onCodeChanged(newCode: String) {
        _enrollmentState.value = _enrollmentState.value.copy(userCode = newCode)
    }

    // --- CHANGE #1: Make this a suspend function and remove viewModelScope ---
    suspend fun checkCurrentMfaStatus() {
        _enrollmentState.value = _enrollmentState.value.copy(isLoading = true, error = null)
        try {
            val (currentAal, _) = supabase.auth.mfa.getAuthenticatorAssuranceLevel()
            Log.d("MfaCheck", "Current session AAL is: $currentAal")

            val hasMfa = currentAal == AuthenticatorAssuranceLevel.AAL2

            _enrollmentState.value = _enrollmentState.value.copy(
                isLoading = false,
                isAlreadyEnabled = hasMfa
            )
        } catch (e: Throwable) {
            Log.e("MfaCheck", "Error checking AAL: ", e)
            _enrollmentState.value = _enrollmentState.value.copy(
                isLoading = false,
                isAlreadyEnabled = false,
                error = "Could not check security status: ${e.message}"
            )
        }
    }

    suspend fun startTotpEnrollment() {
        _enrollmentState.value = _enrollmentState.value.copy(isLoading = true, error = null)
        try {
            val factor = supabase.auth.mfa.enroll(factorType = FactorType.TOTP)

            val totpData = factor.data as? FactorType.TOTP.Response
                ?: throw IllegalStateException("Could not cast factor data to TOTP Response.")

            val qrCodeString = totpData.qrCode

            Log.d("QRCodeCheck", "QR Code: ${qrCodeString}")

            _enrollmentState.value = _enrollmentState.value.copy(
                isLoading = false,
                qrCodeSvg = qrCodeString,
                factorId = factor.id
            )

        } catch (e: Throwable) {
            _enrollmentState.value = _enrollmentState.value.copy(
                isLoading = false,
                error = e.message ?: "An unknown error occurred."
            )
        }
    }

    // --- CHANGE #3: Make this a suspend function and remove viewModelScope ---
    suspend fun verifyAndEnableMfa() {
        val currentState = _enrollmentState.value
        val factorId = currentState.factorId ?: return
        val code = currentState.userCode

        _enrollmentState.value = currentState.copy(isLoading = true, error = null)
        try {
            val challenge = supabase.auth.mfa.createChallenge(factorId)
            supabase.auth.mfa.verifyChallenge(
                factorId = factorId,
                challengeId = challenge.id,
                code = code
            )
            _enrollmentState.value = _enrollmentState.value.copy(
                isLoading = false,
                isVerified = true
            )
        } catch (e: Throwable) {
            _enrollmentState.value = _enrollmentState.value.copy(
                isLoading = false,
                error = "Verification failed: ${e.message}"
            )
        }
    }
}