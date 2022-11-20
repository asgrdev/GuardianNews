package org.asghari.guardiannews.data.remote

import org.asghari.guardiannews.data.models.NewsList
import org.asghari.guardiannews.data.models.Response
import retrofit2.Call
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val guardianNewsApiService: GuardianNewsApiService):RemoteDataSource {
    override suspend fun getNewsById(newsIds: String, showFields: String): NewsList {
       return guardianNewsApiService.getNewsById(newsIds,showFields)
    }

    override suspend fun getNewsListBYQuery(
        page: Int,
        query: String,
        showFields: String
    ): NewsList {
        return guardianNewsApiService.getNewsListBYQuery(page,query,showFields)
    }

    override suspend fun getLastNewsList(page: Int, showFields: String): NewsList {
        return guardianNewsApiService.getLastNewsList(page,showFields)
    }
}