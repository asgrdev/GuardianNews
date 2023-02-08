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
        sections:String,
        page: Int,
        showFields: String
    ) : NewsList {
        if(sections.length>1) {
            return remoteDataSource.getNewsListBYQuery(page, query, sections, showFields)
        }else{
            return remoteDataSource.getNewsListBYQuery(page, query , showFields)
        }
    }

    override suspend fun getLastNewsList(page: Int, sections:String, showFields: String): NewsList {
        if(sections.length>1) {
            return remoteDataSource.getLastNewsList(page, sections, showFields)
        }else{
            return remoteDataSource.getLastNewsList(page, showFields)
        }
    }
}