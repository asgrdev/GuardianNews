package org.asghari.guardiannews.di

import com.test.moviehub.domain.exceptions.IErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.asghari.guardiannews.data.ErrorHandler
import org.asghari.guardiannews.data.local.NewsPagingSource
import org.asghari.guardiannews.data.remote.apiservices.GuardianNewsApiService
import org.asghari.guardiannews.data.remote.RemoteDataSource
import org.asghari.guardiannews.data.remote.RemoteDataSourceImp
import org.asghari.guardiannews.data.remote.apiservices.SectionsApiService
import org.asghari.guardiannews.data.repository.GuardianNewsRepositoryImp
import org.asghari.guardiannews.data.repository.SectionsRepositoryImp
import org.asghari.guardiannews.domain.repositories.GuardianNewsRepository
import org.asghari.guardiannews.domain.repositories.SectionsRepository
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideNewsRepository( remoteDataSource: RemoteDataSource): GuardianNewsRepository
    {
        return GuardianNewsRepositoryImp(remoteDataSource)
    }

    @Provides
    @Singleton
    fun privideSectionsRepository(remoteDataSource: RemoteDataSource):SectionsRepository
    {
        return SectionsRepositoryImp(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideDataSource(guardianNewsApiService: GuardianNewsApiService):NewsPagingSource{
        return NewsPagingSource(guardianNewsApiService)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(guardianNewsApiService: GuardianNewsApiService, sectionsApiService: SectionsApiService):RemoteDataSource{
        return RemoteDataSourceImp(guardianNewsApiService, sectionsApiService)
    }


    @Singleton
    @Provides
    fun provideErrorHandler(): IErrorHandler {
        return ErrorHandler()
    }
}