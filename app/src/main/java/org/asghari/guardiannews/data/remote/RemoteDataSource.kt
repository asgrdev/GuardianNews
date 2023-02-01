package org.asghari.guardiannews.data.remote

import org.asghari.guardiannews.data.models.news.NewsList
import org.asghari.guardiannews.data.models.sections.Sections

interface RemoteDataSource {
    suspend fun getNewsById( newsIds:String,
                             showFields:String): NewsList


    suspend fun getNewsListBYQuery( page:Int,
                                    query:String,
                                    showFields:String): NewsList


    suspend fun getLastNewsList(  page:Int,
                                  showFields:String): NewsList

    suspend fun getSections():Sections

    suspend fun getSectionsById(id:String):Sections
}