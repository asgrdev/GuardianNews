package org.asghari.guardiannews.domain.repositories

import kotlinx.coroutines.flow.Flow
import org.asghari.guardiannews.data.models.sections.Sections

interface SettingsRepository {
   suspend fun addSection(ids:String)

   suspend fun getSelectedSections(): Flow<String>
}