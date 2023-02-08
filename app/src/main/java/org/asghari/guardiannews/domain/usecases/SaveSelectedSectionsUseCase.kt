package org.asghari.guardiannews.domain.usecases

import org.asghari.guardiannews.domain.repositories.SettingsRepository
import javax.inject.Inject

class SaveSelectedSectionsUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {
   operator suspend fun invoke(section:String)
   {
       settingsRepository.getSelectedSections().collect{ currentSections ->
           var newSections:MutableList<String> = currentSections.split(",").toMutableList()
           if(section !in newSections) {
               newSections.add(section)
           }
           val section_ids = newSections.joinToString(",")
           settingsRepository.addSection(section_ids);
       }

   }
}