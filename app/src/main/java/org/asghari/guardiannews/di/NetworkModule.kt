package org.asghari.guardiannews.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.asghari.guardiannews.data.remote.apiservices.GuardianNewsApiService
import org.asghari.guardiannews.data.remote.apiservices.SectionsApiService
import org.asghari.guardiannews.other.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun ProvidesRetrofitNewsApi(): GuardianNewsApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GuardianNewsApiService::class.java)
    }

    @Singleton
    @Provides
    fun ProvidesRetrofitSectionApi(): SectionsApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(SectionsApiService::class.java)
    }
}