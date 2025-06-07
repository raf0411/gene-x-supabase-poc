package kalbe.corp.genexsupabasepoc.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import io.github.jan.supabase.auth.auth
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
            val factors = supabase.auth.mfa.verifiedFactors
            val hasTotp = factors.any { it.factorType == "totp" || it.factorType == "TOTP" }
            _enrollmentState.value = _enrollmentState.value.copy(
                isLoading = false,
                isAlreadyEnabled = hasTotp
            )
        } catch (e: Throwable) {
            _enrollmentState.value = _enrollmentState.value.copy(
                isLoading = false,
                error = "Could not check MFA status: ${e.message}",
                isAlreadyEnabled = false
            )
        }
    }

    suspend fun startTotpEnrollment() {
        _enrollmentState.value = _enrollmentState.value.copy(isLoading = true, error = null)
        try {
            val factor = supabase.auth.mfa.enroll(factorType = FactorType.TOTP)

            Log.d("MfaEnrollDebug", "Enroll call succeeded. Factor ID: ${factor.id}")
            Log.d("MfaEnrollDebug", "Raw 'data' object received: ${factor.data}")
            Log.d("MfaEnrollDebug", "Class of 'data' object: ${factor.data!!::class.simpleName}")

            val dataMap = factor.data as? Map<*, *>
            Log.d("MfaEnrollDebug", "Was cast to Map successful? -> ${dataMap != null}")
            if (dataMap != null) {
                Log.d("MfaEnrollDebug", "All keys found in map: ${dataMap.keys}")
                Log.d("MfaEnrollDebug", "Value for 'qr_code' key: ${dataMap["qrCode"]} or ${dataMap["qr_code"]}")
            }


            val qrCodeString = dataMap?.get("qr_code") as? String

            if (qrCodeString != null) {
                _enrollmentState.value = _enrollmentState.value.copy(
                    isLoading = false,
                    qrCodeSvg = qrCodeString,
                    factorId = factor.id
                )
            } else {
                throw IllegalStateException("QR Code not found in factor data.")
            }
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