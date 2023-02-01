package org.asghari.guardiannews.data.repository


import org.asghari.guardiannews.data.models.news.NewsList
import org.asghari.guardiannews.data.remote.RemoteDataSource
import org.asghari.guardiannews.domain.repositories.GuardianNewsRepository

import javax.inject.Inject


class GuardianNewsRepositoryImp @Inject constructor(
private val remoteDataSource: RemoteDataSource)
    : GuardianNewsRepository {
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