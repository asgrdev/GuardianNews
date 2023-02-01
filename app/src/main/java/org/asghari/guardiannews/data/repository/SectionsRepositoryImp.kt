package org.asghari.guardiannews.data.repository

import org.asghari.guardiannews.data.models.sections.Sections
import org.asghari.guardiannews.data.remote.RemoteDataSource
import org.asghari.guardiannews.domain.repositories.SectionsRepository
import javax.inject.Inject

class SectionsRepositoryImp @Inject constructor(
    private val  remoteDataSource: RemoteDataSource
): SectionsRepository {
    override suspend fun getSections(): Sections {
         return remoteDataSource.getSections()
    }

    override suspend fun getSectionsById(id: String): Sections {
        return remoteDataSource.getSectionsById(id)
    }
}