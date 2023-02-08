package org.asghari.guardiannews.domain.repositories

import org.asghari.guardiannews.data.models.news.NewsList


interface GuardianNewsRepository {
    suspend fun getNewsById( newsIds:String,
                             showFields:String): NewsList


    suspend fun getNewsListBYQuery( query:String,
                                    sections:String,
                                    page:Int,
                                    showFields:String): NewsList

    suspend fun getLastNewsList(  page:Int,
                                  sections:String,
                                  showFields:String): NewsList

}