package org.asghari.guardiannews.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import org.asghari.guardiannews.data.local.PreferencesKeys
import org.asghari.guardiannews.domain.repositories.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>, context: Context) :SettingsRepository {
 override suspend fun addSection(id: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.SECTIONS] = id
        }
    }

  override suspend fun  getSelectedSections(): Flow<String> {
        TODO("Not yet implemented")
    }
}