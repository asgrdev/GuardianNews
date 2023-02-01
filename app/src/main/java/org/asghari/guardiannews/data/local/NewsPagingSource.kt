package org.asghari.guardiannews.data.local

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.asghari.guardiannews.data.models.news.NewsList
import org.asghari.guardiannews.data.models.news.Result
import org.asghari.guardiannews.data.remote.apiservices.GuardianNewsApiService
import javax.inject.Inject

class NewsPagingSource @Inject constructor(
    private val guardianNewsApiService: GuardianNewsApiService,
    private val query: String? = null
): PagingSource<Int, Result>() {
    private lateinit var newsList: NewsList

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let{
                anchorPosition ->
            Log.d(">>>>>>>>rrrgg",">>>>>>>>")
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
         }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val nextPageNumber = params.key ?: 1
            Log.d(">>>>>>>>rrr","===="+nextPageNumber)


         //   NewsList = guardianNewsApiService.getNewsListBYQuery(nextPageNumber,query,"all")
            newsList = guardianNewsApiService.getLastNewsList(nextPageNumber,"starRating,thumbnail,short-url")
            Log.d(">>>>>>>>rrr","=="+newsList.response)

            LoadResult.Page(
                data = newsList.response.results,
                prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < newsList.response.total) nextPageNumber + 1 else null
            )
        }
        catch (e: Exception) {
            Log.d(">>>>>>>>rrrbb",e.toString())
            LoadResult.Error(e)
        }


    }
}