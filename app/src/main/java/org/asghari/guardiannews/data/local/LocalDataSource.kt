package org.asghari.guardiannews.data.local

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
   suspend fun addSection(ids:String)
   suspend fun removeSection(ids:String)
   suspend fun getSelectedSections(): Flow<String>
}