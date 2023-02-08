package org.asghari.guardiannews.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.asghari.guardiannews.App

class LocalDataSourceImpl(appContext: Context):LocalDataSource {
    private val SETTINGS = "Settings"
    private  val Context.settingsPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "Settings"

    )
    val dataStore =    appContext.settingsPreferencesDataStore

    override suspend fun addSection(ids: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.SECTIONS] = ids
        }
    }

    override suspend fun removeSection(ids: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getSelectedSections(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[PreferencesKeys.SECTIONS] ?: ""
        }
    }
}


object PreferencesKeys{
    val SECTIONS = stringPreferencesKey("Sections")
}