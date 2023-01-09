package org.asghari.guardiannews.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.asghari.guardiannews.data.local.NewsDao
import org.asghari.guardiannews.domain.LastNewsListUseCase
import org.asghari.guardiannews.other.NewsListState
import org.asghari.guardiannews.presentation.adapters.NewsListAdapter
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor
    (private val lastNewsListUseCase: LastNewsListUseCase):
    ViewModel() {

    private val _newsList :  MutableStateFlow<NewsListState>  = MutableStateFlow(NewsListState.Loading)
    val newsList = mutableStateOf<NewsListState>(NewsListState.Loading)

    init {
        getNewsList()
    }
    private fun getNewsList(){

        CoroutineScope(Dispatchers.IO).launch {
            _newsList.value = NewsListState.Loading
            var call =  lastNewsListUseCase.Call();
            call?.let{

                if(it==null){
                    _newsList.value = NewsListState.Error("Error!!",null)
                }
                else {
                    _newsList.value = NewsListState.Success("",it)

                }
                newsList.value = _newsList.value

            }

        }

    }
}