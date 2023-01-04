package org.asghari.guardiannews.domain

import androidx.paging.PagingData
import org.asghari.guardiannews.data.models.Response
import org.asghari.guardiannews.data.models.Result
import kotlinx.coroutines.flow.Flow
import org.asghari.guardiannews.data.models.NewsList


interface GuardianNewsRepository {
    suspend fun getNewsById( newsIds:String,
                             showFields:String): NewsList


    suspend fun getNewsListBYQuery( query:String,
                                    page:Int,
                                    showFields:String): NewsList

    suspend fun getLastNewsList(  page:Int,
                                  showFields:String): NewsList

}