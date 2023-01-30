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
import org.asghari.guardiannews.data.models.NewsList
import org.asghari.guardiannews.data.models.Response
import org.asghari.guardiannews.domain.LastNewsListUseCase
import org.asghari.guardiannews.domain.SearchInNewsListUseCase
import org.asghari.guardiannews.other.NewsListState
import org.asghari.guardiannews.presentation.adapters.NewsListAdapter
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor
    (private val lastNewsListUseCase: LastNewsListUseCase,private val searchInNewsListUseCase: SearchInNewsListUseCase):
    ViewModel() {

    private val _newsList: MutableSharedFlow<NewsListState> = MutableStateFlow(NewsListState.Loading("",null))
    val newsList = mutableStateOf<NewsListState>(NewsListState.Loading("",null))
    lateinit var call:NewsList

    var tmpNewsList:NewsList? = null
    var currentSearchQuery:String = ""
    var current_page:Int = 1
    init {
        getNewsList()
    }

     fun getNewsList(query:String="") {
         current_page = 1
         currentSearchQuery = query
         viewModelScope.launch {
            _newsList.emit(NewsListState.Loading("",null))

             if(query.equals("")) {
                  call = lastNewsListUseCase.run()
             }
             else{
                  call = searchInNewsListUseCase(query,current_page);
             }
            call?.let {
                if (it == null) {
                    _newsList.emit(NewsListState.Error("Error!!", null))
                } else {
                    tmpNewsList = it
                    _newsList.emit(NewsListState.Success("", it))

                }
              _newsList.collectLatest {
                newsList.value = it
            }

            }

        }

    }

    fun LoadMore(query:String="") {
        current_page++
        currentSearchQuery = query
        Log.d("Loa", "Show" + current_page)
        viewModelScope.launch {
            _newsList.emit(NewsListState.Loading("",tmpNewsList))

            if(query.equals("")) {
                call = lastNewsListUseCase.run(current_page);
            }
            else{
                call = searchInNewsListUseCase(query,current_page);
            }
            call?.let {

                try {
                    tmpNewsList?.let { tmpedNewsList->
                        tmpedNewsList.response.results += it.response.results
                      _newsList.emit(NewsListState.Success("", tmpedNewsList))
                    }
                }
                catch (e:HttpException){
                    _newsList.emit(NewsListState.Error("Error!!", null))
                }
            }
            _newsList.collectLatest {
                newsList.value = it
            }
        }

    }
}