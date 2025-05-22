package kalbe.corp.genexsupabasepoc.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.postgrest.exception.PostgrestRestException
import kalbe.corp.genexsupabasepoc.data.AuthRepository
import kalbe.corp.genexsupabasepoc.data.ProfilePreferencesRepository
import kalbe.corp.genexsupabasepoc.data.ProfileRepository
import kalbe.corp.genexsupabasepoc.data.UserRepository
import kalbe.corp.genexsupabasepoc.models.Profile
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileRepository: ProfileRepository,
    private val userRepository: UserRepository,
    private val profilePreferences: ProfilePreferencesRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val tag = "ProfileViewModel"

    private val _availableProfiles = MutableStateFlow<List<Profile>>(emptyList())
    val availableProfiles: StateFlow<List<Profile>> = _availableProfiles.asStateFlow()

    private val _selectedProfileId = MutableStateFlow<String?>(null)
    val selectedProfileId: StateFlow<String?> = _selectedProfileId.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _navigateToLogin = MutableSharedFlow<Unit>()
    val navigateToLogin = _navigateToLogin.asSharedFlow()

    val selectedProfile: StateFlow<Profile?> = combine(
        selectedProfileId, availableProfiles
    ) { id, profiles ->
        profiles.find { it.id == id }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    init {
        Log.d(tag, "ViewModel Initialized - Loading initial profile data.")
        loadInitialData()
    }

    fun loadInitialData() {
        viewModelScope.launch {
            Log.e("CHECK_VIEWMODEL", "*** loadInitialData: Execution STARTED ***")
            _isLoading.value = true
            _errorMessage.value = null
            try {
                Log.w(tag, "loadInitialData: USING TEST REPO FUNCTION to force API call!")

//                val profiles = profileRepository.getCurrentUserProfiles_FORCE_API_CALL_FOR_TEST()
                val profiles = profileRepository.getCurrentUserProfiles(userRepository)

                _availableProfiles.value = profiles
                Log.i(tag, "loadInitialData: Profile fetch SUCCEEDED. Count: ${profiles.size}")
                val lastSelectedId: String? = profilePreferences.getSelectedProfileId().first()
                if (lastSelectedId != null && profiles.any { it.id == lastSelectedId }) {
                    _selectedProfileId.value = lastSelectedId
                } else {
                    _selectedProfileId.value = profiles.firstOrNull()?.id
                }
                Log.d(tag, "Selected profile ID set to: ${_selectedProfileId.value}")

                _selectedProfileId.value?.let {
                    profilePreferences.saveSelectedProfileId(it)
                }

            } catch (e: Exception) {
                Log.e("CHECK_VIEWMODEL", ">>> EXCEPTION CAUGHT in ViewModel <<<")
                Log.e("CHECK_VIEWMODEL", ">>> Type: ${e::class.java.simpleName}")
                Log.e("CHECK_VIEWMODEL", ">>> Message: ${e.message}")
                Log.e("CHECK_VIEWMODEL", ">>> Cause: ${e.cause}")
                e.printStackTrace()

                val isAuthError = (e is PostgrestRestException && e.message?.contains("JWT expired", ignoreCase = true) == true) ||
                        e.message?.contains("401") == true ||
                        e.cause?.message?.contains("401") == true

                Log.w(tag, "loadInitialData: isAuthError check result: $isAuthError")

                if (isAuthError) {
                    Log.w(tag, "loadInitialData: Auth error path starting (EXPECTED after expiry)...")
                    _errorMessage.value = "Session expired. Please log in again."

                    try {
                        authRepository.logout()
                        _navigateToLogin.emit(Unit)
                    } catch (logoutError: Exception) {
                        logoutError.printStackTrace()
                        _navigateToLogin.emit(Unit)
                    }
                } else {
                    Log.e(tag, "loadInitialData: Non-Auth error path taken.")
                    _errorMessage.value = "Failed to load profile data: ${e.message ?: "Unknown error"}"
                    _availableProfiles.value = emptyList()
                }
            } finally {
                _isLoading.value = false
                Log.e("CHECK_VIEWMODEL", "*** loadInitialData: Execution FINISHED (in finally) ***")
            }
        }
    }

    fun switchProfile(newProfileId: String) {
        if (newProfileId != _selectedProfileId.value && _availableProfiles.value.any{ it.id == newProfileId}) {
            Log.d(tag, "Switching profile to ID: $newProfileId")
            _selectedProfileId.value = newProfileId
            viewModelScope.launch {
                try {
                    profilePreferences.saveSelectedProfileId(newProfileId)
                } catch (e: Exception) {
                    Log.e(tag, "Failed to save switched profile pref: ${e.message}", e)
                    _errorMessage.value = "Failed to save profile selection."
                }
            }
        } else {
            Log.w(tag, "Attempted to switch to invalid or same profile ID: $newProfileId")
        }
    }
}

class ProfileViewModelFactory(
    private val profileRepository: ProfileRepository,
    private val userRepository: UserRepository,
    private val profilePreferencesRepository: ProfilePreferencesRepository,
    private val authRepository: AuthRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(profileRepository, userRepository, profilePreferencesRepository, authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}