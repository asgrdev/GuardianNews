package org.asghari.guardiannews.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
 import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.asghari.guardiannews.data.ErrorHandler
import org.asghari.guardiannews.data.local.LocalDataSource
import org.asghari.guardiannews.data.local.LocalDataSourceImpl
import org.asghari.guardiannews.data.remote.apiservices.GuardianNewsApiService
import org.asghari.guardiannews.data.remote.RemoteDataSource
import org.asghari.guardiannews.data.remote.RemoteDataSourceImp
import org.asghari.guardiannews.data.remote.apiservices.SectionsApiService
import org.asghari.guardiannews.data.repository.GuardianNewsRepositoryImp
import org.asghari.guardiannews.data.repository.SectionsRepositoryImp
import org.asghari.guardiannews.data.repository.SettingsRepositoryImpl
import org.asghari.guardiannews.domain.exceptions.IErrorHandler
import org.asghari.guardiannews.domain.repositories.GuardianNewsRepository
import org.asghari.guardiannews.domain.repositories.SectionsRepository
import org.asghari.guardiannews.domain.repositories.SettingsRepository
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
    fun provideLocalDataSource( @ApplicationContext context: Context):LocalDataSource{
        return LocalDataSourceImpl(appContext = context)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(localDataSource: LocalDataSource):SettingsRepository{
        return SettingsRepositoryImpl(localDataSource)
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