package org.asghari.guardiannews.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.moviehub.domain.base.UseCaseCallback
import com.test.moviehub.domain.exceptions.ErrorModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.asghari.guardiannews.data.models.NewsList
import org.asghari.guardiannews.domain.NewsDetailsUseCase
import org.asghari.guardiannews.other.NewsListState
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    private val newsDetailsUseCase: NewsDetailsUseCase
) : ViewModel(){

    private val _newsDetails : MutableStateFlow<NewsListState> = MutableStateFlow(NewsListState.Loading)
    val  newsDetails = mutableStateOf<NewsListState>(NewsListState.Loading)

    fun getNewsDetails(newsId:String,newsFields:String)
    {
        viewModelScope.launch {
            newsDetailsUseCase.call(newsId, onResult = object:UseCaseCallback<NewsList>{
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