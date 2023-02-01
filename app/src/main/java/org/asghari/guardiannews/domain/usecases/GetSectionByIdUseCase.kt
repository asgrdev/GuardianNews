package org.asghari.guardiannews.domain.usecases

import org.asghari.guardiannews.domain.repositories.SectionsRepository
import javax.inject.Inject

class GetSectionByIdUseCase @Inject constructor(private val sectionsRepository: SectionsRepository) {
    operator suspend fun invoke(id:String)=sectionsRepository.getSectionsById(id)
}