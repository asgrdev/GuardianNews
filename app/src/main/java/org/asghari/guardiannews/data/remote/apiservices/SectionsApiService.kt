package org.asghari.guardiannews.data.remote.apiservices

import org.asghari.guardiannews.BuildConfig
import org.asghari.guardiannews.data.models.news.NewsList
import org.asghari.guardiannews.data.models.sections.Sections
import retrofit2.http.GET
import retrofit2.http.Query

interface SectionsApiService {
    @GET("/sections")
    suspend fun getSections(@Query("api-key") ApiKey:String= BuildConfig.API_KEY): Sections

    @GET("/sections")
    suspend fun getSectionsById(@Query("q") Query:String?,@Query("api-key") ApiKey:String= BuildConfig.API_KEY): Sections

}