package org.asghari.guardiannews.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

class SettingsDataStore {
    private val SETTINGS = "Settings"
    private val Context.settingsPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "Settings"

    )


}

object PreferencesKeys{
    val SECTIONS = stringPreferencesKey("Sections")
}