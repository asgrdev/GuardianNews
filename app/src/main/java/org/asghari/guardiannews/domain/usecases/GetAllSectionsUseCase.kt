package org.asghari.guardiannews.domain.usecases

import org.asghari.guardiannews.domain.repositories.SectionsRepository
import javax.inject.Inject

class GetAllSectionsUseCase @Inject constructor(private val sectionsRepository: SectionsRepository){
    operator  suspend fun invoke()=sectionsRepository.getSections()
}