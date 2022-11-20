package org.asghari.guardiannews.di

import com.test.moviehub.domain.exceptions.IErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.asghari.guardiannews.data.ErrorHandler
import org.asghari.guardiannews.data.local.NewsPagingSource
import org.asghari.guardiannews.data.remote.GuardianNewsApiService
import org.asghari.guardiannews.data.remote.RemoteDataSource
import org.asghari.guardiannews.data.remote.RemoteDataSourceImp
import org.asghari.guardiannews.data.repository.GuardianNewsRepositoryImp
import org.asghari.guardiannews.domain.GuardianNewsRepository
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideRepository(guardianNewsApiService: GuardianNewsApiService, remoteDataSource: RemoteDataSource):GuardianNewsRepository
    {
        return GuardianNewsRepositoryImp(guardianNewsApiService,remoteDataSource)
    }


    @Provides
    @Singleton
    fun provideDataSource(guardianNewsApiService: GuardianNewsApiService):NewsPagingSource{
        return NewsPagingSource(guardianNewsApiService)
    }


    @Provides
    @Singleton
    fun provideRemoteDataSource(guardianNewsApiService: GuardianNewsApiService):RemoteDataSource{
        return RemoteDataSourceImp(guardianNewsApiService)
    }

    @Singleton
    @Provides
    fun provideErrorHandler(): IErrorHandler {
        return ErrorHandler()
    }
}