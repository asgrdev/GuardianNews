package org.asghari.guardiannews.domain.usecases

import android.util.Log
import kotlinx.coroutines.flow.Flow
import org.asghari.guardiannews.domain.repositories.SettingsRepository
import javax.inject.Inject

class GetSelectedSectionsUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {
    operator suspend fun invoke(): Flow<String> {

        return settingsRepository.getSelectedSections()

    }
}