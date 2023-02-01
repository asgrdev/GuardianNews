package org.asghari.guardiannews.presentation

import org.asghari.guardiannews.data.models.news.NewsList

data class NewsListState_(
    val isLoading:Boolean = false,
    val error: String = "",
    val data: NewsList? = null
)
