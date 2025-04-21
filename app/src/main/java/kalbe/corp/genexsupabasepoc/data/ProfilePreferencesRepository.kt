package kalbe.corp.genexsupabasepoc.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.io.IOException

val Context.profileDataStore: DataStore<Preferences> by preferencesDataStore(name = "user_profile_preferences")

interface ProfilePreferencesRepository {
    suspend fun saveSelectedProfileId(profileId: String)

    /**
     * Gets a Flow that emits the last selected profile ID.
     * Emits null if no profile ID has been saved yet.
     * The Flow automatically emits new values if the preference changes.
     * @return Flow emitting the profile ID (String?...) or null.
     */
    fun getSelectedProfileId(): Flow<String?>
}

class ProfilePreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : ProfilePreferencesRepository {
    private companion object {
        val SELECTED_PROFILE_ID_KEY = stringPreferencesKey("selected_profile_id")
        const val TAG = "ProfilePrefsRepo"
    }

    override suspend fun saveSelectedProfileId(profileId: String) {
        try {
            dataStore.edit { preferences ->
                preferences[SELECTED_PROFILE_ID_KEY] = profileId
                Log.d(TAG, "Saved selected profile ID: $profileId")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error saving selected profile ID", e)
        }
    }

    override fun getSelectedProfileId(): Flow<String?> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    Log.e(TAG, "Error reading profile preferences.", exception)
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val profileId = preferences[SELECTED_PROFILE_ID_KEY]
                Log.d(TAG, "Retrieved selected profile ID from DataStore: $profileId")
                profileId.toString()
            }
    }
}