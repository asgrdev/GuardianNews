package org.asghari.guardiannews.other

import org.asghari.guardiannews.data.models.news.NewsList

sealed class NewsListState{
    data class Success(val message:String,val data: NewsList):NewsListState()
    data class Error(val message:String,val data: NewsList? =null):NewsListState()
    data class Loading(val message:String,val data: NewsList?):NewsListState()


}
