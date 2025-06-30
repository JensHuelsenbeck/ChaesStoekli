package com.example.cheas_stoeckli.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPrefRepository(
    context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_settings")
    private val dataStore: DataStore<Preferences> = context.dataStore

    // hält Key:Value Teile
    private object Keys {
        val HAS_SEEN_INFO_DIALOG = booleanPreferencesKey("has_seen_info_dialog")
    }

    // ColdFlow zum Abrufen
    val hasSeenLocationDialog: Flow<Boolean> =
        dataStore.data.map { prefs ->
            prefs[Keys.HAS_SEEN_INFO_DIALOG] ?: false
        }


    suspend fun setHasSeenInfoDialog(seen: Boolean) {
        dataStore.edit { prefs ->
            prefs[Keys.HAS_SEEN_INFO_DIALOG] = seen
        }
    }
}