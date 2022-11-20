package org.asghari.guardiannews.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.moviehub.domain.base.UseCaseCallback
import com.test.moviehub.domain.exceptions.ErrorModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.asghari.guardiannews.data.models.NewsList
import org.asghari.guardiannews.domain.NewsDetailsUseCase
import org.asghari.guardiannews.presentation.NewsListState
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    private val newsDetailsUseCase: NewsDetailsUseCase
) : ViewModel(){

    private val _newsDetails by lazy { MutableLiveData<NewsListState>() }
    val newsDetails: MutableLiveData<NewsListState> get() = _newsDetails

    fun getNewsDetails(newsId:String,newsFields:String)
    {
        viewModelScope.launch {
            _newsDetails.value = NewsListState(isLoading = true,"",null)
            newsDetailsUseCase.call(newsId, onResult = object:UseCaseCallback<NewsList>{
                override fun onError(errorModel: ErrorModel?) {
                    _newsDetails.value = NewsListState(isLoading = false,
                        errorModel?.message!! ,null, null)
                }

                override fun onSuccess(result: NewsList) {
                    _newsDetails.value = NewsListState(isLoading = false,"",
                        null, result.response.results.get(0)!!)
                }
            })
        }
    }

}