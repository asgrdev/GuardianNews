package org.asghari.guardiannews.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.asghari.guardiannews.data.local.LocalDataSource
import org.asghari.guardiannews.data.local.PreferencesKeys
import org.asghari.guardiannews.domain.repositories.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) :SettingsRepository {
 override suspend fun addSection(ids: String) {
        localDataSource.addSection(ids)
    }

    override suspend fun  getSelectedSections(): Flow<String> {
      return localDataSource.getSelectedSections()
    }
}