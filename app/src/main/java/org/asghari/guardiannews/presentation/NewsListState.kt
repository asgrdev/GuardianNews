package org.asghari.guardiannews.presentation

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.asghari.guardiannews.data.local.NewsDao
import org.asghari.guardiannews.data.models.NewsList
import org.asghari.guardiannews.data.models.Result

data class NewsListState(
    val isLoading:Boolean = false,
    val error: String = "",
    val data: NewsList? = null
)
