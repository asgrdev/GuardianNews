package org.asghari.guardiannews.data.remote.apiservices

import org.asghari.guardiannews.BuildConfig
import org.asghari.guardiannews.data.models.news.NewsList
import retrofit2.http.GET
import retrofit2.http.Query

interface GuardianNewsApiService {
    @GET("/search")
    suspend fun getNewsById(@Query("ids")  NewsIds:String,
                            @Query("show-fields") ShowFields:String,
                            @Query("api-key") ApiKey:String= BuildConfig.API_KEY): NewsList

    @GET("/search")
    suspend fun getNewsListBYQuery(@Query("page") Page:Int,
                                   @Query("q") Query:String?,
                                   @Query("section") Sections:String?,
                                   @Query("show-fields") ShowFields:String,
                                   @Query("api-key") ApiKey:String= BuildConfig.API_KEY): NewsList

    @GET("/search")
    suspend fun getLastNewsList(@Query("page") Page:Int,
                                @Query("section") Sections:String?,
                                @Query("show-fields") ShowFields:String,
                                @Query("api-key") ApiKey:String= BuildConfig.API_KEY): NewsList

    @GET("/search")
    suspend fun getNewsListBYQuery(@Query("page") Page:Int,
                                   @Query("q") Query:String?,
                                   @Query("show-fields") ShowFields:String,
                                   @Query("api-key") ApiKey:String= BuildConfig.API_KEY): NewsList

    @GET("/search")
    suspend fun getLastNewsList(@Query("page") Page:Int,
                                @Query("show-fields") ShowFields:String,
                                @Query("api-key") ApiKey:String= BuildConfig.API_KEY): NewsList

}