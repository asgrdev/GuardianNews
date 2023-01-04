package org.asghari.guardiannews.data.repository


import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import org.asghari.guardiannews.data.local.NewsPagingSource
import org.asghari.guardiannews.data.models.NewsList
import org.asghari.guardiannews.data.models.Response
import org.asghari.guardiannews.data.models.Result
import org.asghari.guardiannews.data.remote.GuardianNewsApiService
import org.asghari.guardiannews.data.remote.RemoteDataSource
import org.asghari.guardiannews.domain.GuardianNewsRepository
import org.asghari.guardiannews.other.Constants
import retrofit2.Call
import retrofit2.Callback

import javax.inject.Inject
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit




class GuardianNewsRepositoryImp @Inject constructor(
    private val guardianNewsApiService: GuardianNewsApiService,
private val remoteDataSource: RemoteDataSource)
    :GuardianNewsRepository {
    override suspend fun getNewsById(newsIds: String, showFields: String): NewsList {
        return remoteDataSource.getNewsById(newsIds,showFields)
    }

    override suspend fun getNewsListBYQuery(
        query: String,
        page: Int,
        showFields: String
    ) : NewsList {
        return remoteDataSource.getNewsListBYQuery(page,query,showFields)
    }

    override suspend fun getLastNewsList(page: Int, showFields: String): NewsList {
        return remoteDataSource.getLastNewsList(page,showFields)
    }
}