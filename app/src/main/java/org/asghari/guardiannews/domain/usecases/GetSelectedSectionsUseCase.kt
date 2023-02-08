package org.asghari.guardiannews.domain.usecases

import org.asghari.guardiannews.domain.repositories.SettingsRepository
import javax.inject.Inject

class GetSelectedSectionsUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {
    operator suspend fun invoke(): List<String> {
        var selectedSections = listOf<String>()
        settingsRepository.getSelectedSections().collect { currentSections ->
            selectedSections = currentSections.split(",")
        }
        return selectedSections
    }
}