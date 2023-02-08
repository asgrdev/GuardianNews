package org.asghari.guardiannews.domain.usecases

import org.asghari.guardiannews.domain.repositories.SettingsRepository
import javax.inject.Inject

class DeleteSectionByIdUsecase @Inject constructor(private val settingsRepository: SettingsRepository) {
    operator suspend fun invoke(sectionId: String) {
        settingsRepository.getSelectedSections().collect { currentSections ->
            var newSections: MutableList<String> = currentSections.split(",").toMutableList()
            if (sectionId in newSections) {
                newSections.remove(sectionId)
            }
            val section_ids = newSections.joinToString(",")
            settingsRepository.addSection(section_ids);
        }
    }
}