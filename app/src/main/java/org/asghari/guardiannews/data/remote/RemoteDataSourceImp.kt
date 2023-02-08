package org.asghari.guardiannews.data.remote

import org.asghari.guardiannews.data.models.news.NewsList
import org.asghari.guardiannews.data.models.sections.Sections
import org.asghari.guardiannews.data.remote.apiservices.GuardianNewsApiService
import org.asghari.guardiannews.data.remote.apiservices.SectionsApiService
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val guardianNewsApiService: GuardianNewsApiService
, private val sectionsApiService: SectionsApiService
):RemoteDataSource {
    override suspend fun getNewsById(newsIds: String, showFields: String): NewsList {
       return guardianNewsApiService.getNewsById(newsIds,showFields)
    }

    override suspend fun getNewsListBYQuery(
        page: Int,
        query: String,
        sections:String,
        showFields: String
    ): NewsList {
        return guardianNewsApiService.getNewsListBYQuery(page,query,sections,showFields)
    }

    override suspend fun getNewsListBYQuery(
        page: Int,
        query: String,
        showFields: String
    ): NewsList {
        return guardianNewsApiService.getNewsListBYQuery(page,query, showFields)
    }

    override suspend fun getLastNewsList(page: Int,sections:String, showFields: String): NewsList {
        return guardianNewsApiService.getLastNewsList(page,sections,showFields)
    }

    override suspend fun getLastNewsList(page: Int, showFields: String): NewsList {
        return guardianNewsApiService.getLastNewsList(page ,showFields)
    }

    override suspend fun getSections(): Sections {
        return sectionsApiService.getSections()
    }

    override suspend fun getSectionsById(id: String): Sections {
        return sectionsApiService.getSectionsById(id);
    }


}