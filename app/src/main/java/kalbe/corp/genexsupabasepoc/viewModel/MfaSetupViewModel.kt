package kalbe.corp.genexsupabasepoc.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.mfa.AuthenticatorAssuranceLevel
import io.github.jan.supabase.auth.mfa.FactorType
import kalbe.corp.genexsupabasepoc.data.network.supabaseClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// Your State class is already perfect for the new solution. No changes needed here.
data class MfaEnrollmentState(
    val qrCodeSvg: String? = null,
    val factorId: String? = null,
    val setupKey: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val userCode: String = "",
    val isVerified: Boolean = false,
    val isAlreadyEnabled: Boolean? = null,
    val otpUri: String? = null,
)

class MfaSetupViewModel : ViewModel() {

    private val _enrollmentState = MutableStateFlow(MfaEnrollmentState())
    val enrollmentState: StateFlow<MfaEnrollmentState> = _enrollmentState.asStateFlow()

    private val supabase = supabaseClient

    fun onCodeChanged(newCode: String) {
        _enrollmentState.update { it.copy(userCode = newCode) }
    }

    fun checkCurrentMfaStatus() {
        viewModelScope.launch {
            _enrollmentState.update { it.copy(isLoading = true, error = null) }
            try {
                val (currentAal, _) = supabase.auth.mfa.getAuthenticatorAssuranceLevel()
                Log.d("MfaCheck", "Current session AAL is: $currentAal")

                val hasMfa = currentAal == AuthenticatorAssuranceLevel.AAL2

                _enrollmentState.update {
                    it.copy(isLoading = false, isAlreadyEnabled = hasMfa)
                }
            } catch (e: Throwable) {
                Log.e("MfaCheck", "Error checking AAL: ", e)
                _enrollmentState.update {
                    it.copy(
                        isLoading = false,
                        isAlreadyEnabled = false,
                        error = "Could not check security status: ${e.message}"
                    )
                }
            }
        }
    }

    fun startTotpEnrollment() {
        viewModelScope.launch {
            _enrollmentState.update { it.copy(isLoading = true, error = null) }
            try {
                val factor = supabase.auth.mfa.enroll(factorType = FactorType.TOTP)

                val totpData = factor.data as? FactorType.TOTP.Response
                    ?: throw IllegalStateException("Could not cast factor data to TOTP Response.")

                val qrCodeString = totpData.qrCode
                val secretKey = totpData.secret

                val otpUri = totpData.uri

                _enrollmentState.update {
                    it.copy(
                        isLoading = false,
                        qrCodeSvg = qrCodeString,
                        otpUri = otpUri,
                        setupKey = secretKey,
                        factorId = factor.id
                    )
                }

            } catch (e: Throwable) {
                Log.e("MfaEnroll", "Error enrolling factor", e)
                _enrollmentState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "An unknown error occurred."
                    )
                }
            }
        }
    }

    fun verifyAndEnableMfa() {
        viewModelScope.launch {
            val currentState = _enrollmentState.value
            val factorId = currentState.factorId ?: return@launch
            val code = currentState.userCode

            _enrollmentState.update { it.copy(isLoading = true, error = null) }
            try {
                val challenge = supabase.auth.mfa.createChallenge(factorId)
                supabase.auth.mfa.verifyChallenge(
                    factorId = factorId,
                    challengeId = challenge.id,
                    code = code
                )
                _enrollmentState.update { it.copy(isLoading = false, isVerified = true) }
            } catch (e: Throwable) {
                Log.e("MfaVerify", "Error verifying challenge", e)
                _enrollmentState.update {
                    it.copy(
                        isLoading = false,
                        error = "Verification failed: ${e.message}"
                    )
                }
            }
        }
    }
}