package org.asghari.guardiannews.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.asghari.guardiannews.data.models.news.NewsList
import org.asghari.guardiannews.domain.base.UseCaseCallback
import org.asghari.guardiannews.domain.exceptions.ErrorModel
import org.asghari.guardiannews.domain.usecases.NewsDetailsUseCase
import org.asghari.guardiannews.other.NewsListState
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    private val newsDetailsUseCase: NewsDetailsUseCase
) : ViewModel(){

    private val _newsDetails : MutableStateFlow<NewsListState> = MutableStateFlow(NewsListState.Loading("",null))
    val  newsDetails = mutableStateOf<NewsListState>(NewsListState.Loading("",null))

    fun getNewsDetails(newsId:String,newsFields:String)
    {
        viewModelScope.launch {
            newsDetailsUseCase.call(newsId, onResult = object: UseCaseCallback<NewsList> {
                override fun onError(errorModel: ErrorModel?) {
                    _newsDetails.value = NewsListState.Error(errorModel?.message!!+"error", null)
                    newsDetails.value = _newsDetails.value
                }

                override fun onSuccess(result: NewsList) {
                    _newsDetails.value = NewsListState.Success("", result)
                    newsDetails.value = _newsDetails.value
                }
            })
        }
    }

}