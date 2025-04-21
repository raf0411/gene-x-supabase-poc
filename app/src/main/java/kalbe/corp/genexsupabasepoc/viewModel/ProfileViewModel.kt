package kalbe.corp.genexsupabasepoc.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kalbe.corp.genexsupabasepoc.data.ProfilePreferencesRepository
import kalbe.corp.genexsupabasepoc.data.ProfileRepository
import kalbe.corp.genexsupabasepoc.data.UserRepository
import kalbe.corp.genexsupabasepoc.models.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileRepository: ProfileRepository,
    private val userRepository: UserRepository,
    private val profilePreferences: ProfilePreferencesRepository
) : ViewModel() {
    private val _availableProfiles = MutableStateFlow<List<Profile>>(emptyList())
    val availableProfiles: StateFlow<List<Profile>> = _availableProfiles.asStateFlow()

    private val _selectedProfileId = MutableStateFlow<String?>(null)
    val selectedProfileId: StateFlow<String?> = _selectedProfileId.asStateFlow()

    val selectedProfile: StateFlow<Profile?> = combine(
        selectedProfileId, availableProfiles
    ) { id, profiles ->
        profiles.find { it.id == id }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _availableProfiles.value = profileRepository.getCurrentUserProfiles(userRepository)

            val lastSelectedId: String?  = profilePreferences.getSelectedProfileId().first()

            if (lastSelectedId != null && _availableProfiles.value.any { it.id == lastSelectedId }) {
                _selectedProfileId.value = lastSelectedId
            } else {
                _selectedProfileId.value = _availableProfiles.value.firstOrNull()?.id
            }

            _selectedProfileId.value?.let {
                profilePreferences.saveSelectedProfileId(it)
            }
        }
    }

    fun switchProfile(newProfileId: String) {
        if (newProfileId != _selectedProfileId.value) {
            _selectedProfileId.value = newProfileId
            viewModelScope.launch {
                profilePreferences.saveSelectedProfileId(newProfileId)
            }
        }
    }
}

class ProfileViewModelFactory(
    private val profileRepository: ProfileRepository,
    private val userRepository: UserRepository,
    private val profilePreferencesRepository: ProfilePreferencesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(profileRepository, userRepository, profilePreferencesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}