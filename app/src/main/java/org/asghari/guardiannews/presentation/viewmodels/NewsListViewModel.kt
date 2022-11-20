package org.asghari.guardiannews.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.asghari.guardiannews.data.local.NewsDao
import org.asghari.guardiannews.domain.LastNewsListUseCase
import org.asghari.guardiannews.other.Resource
import org.asghari.guardiannews.presentation.NewsListState
import org.asghari.guardiannews.presentation.adapters.NewsListAdapter
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor
    (private val lastNewsListUseCase: LastNewsListUseCase):
    ViewModel() {

    private val _newsList by lazy { MutableLiveData<NewsListState>() }
    val newsList:MutableLiveData<NewsListState> get() = _newsList

    init {
        Log.d("4bb>>>>>>>>>>>>",">>>>>>>>>>>>>>>>>>>>>>>>>>>>ss".toString())
        getNewsList()
    }
    private fun getNewsList(){

        Log.d("5bbb>>>>>>>>>>>>",">>>>>>>>>>>>>>>>>>>>>>>>>>>>ss".toString())
        viewModelScope.launch {
            _newsList.value = NewsListState(isLoading = true,"",null)
            lastNewsListUseCase.Call().cachedIn(viewModelScope).collectLatest {

                if(it==null){
                    _newsList.value = NewsListState(isLoading = false,"Error!!",null)

                }
                else {
                    _newsList.value = NewsListState(isLoading = false, error = "", it)
                }

            }

        }

    }
}