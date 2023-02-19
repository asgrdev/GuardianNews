package org.asghari.guardiannews.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.asghari.guardiannews.data.models.news.NewsList
import org.asghari.guardiannews.domain.usecases.GetSelectedSectionsUseCase
import org.asghari.guardiannews.domain.usecases.LastNewsListUseCase
import org.asghari.guardiannews.domain.usecases.SearchInNewsListUseCase
import org.asghari.guardiannews.other.NewsListState
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor
    (private val lastNewsListUseCase: LastNewsListUseCase
    , private val searchInNewsListUseCase: SearchInNewsListUseCase
    ,private val getSelectedSectionsUseCase: GetSelectedSectionsUseCase
):ViewModel() {

    private val _newsList: MutableSharedFlow<NewsListState> =
        MutableStateFlow(NewsListState.Loading("", null))
    val newsList = mutableStateOf<NewsListState>(NewsListState.Loading("", null))

    private val _SelectedSectionsList: MutableStateFlow<List<String>> = MutableStateFlow(listOf())
    val selectedSectionsList = mutableStateOf<List<String>>(listOf())

    lateinit var call: NewsList

    var tmpNewsList: NewsList? = null
    var currentSearchQuery: String = ""
    var current_page: Int = 1

    init {
        getNewsList()
    }

    fun getNewsList(query: String = "",sections:String="") {
        current_page = 1
        currentSearchQuery = query
        viewModelScope.launch {
            _newsList.emit(NewsListState.Loading("", null))
            if (sections.equals("")) {
                getSelectedSectionsUseCase().collect {
                    getList(query, it)
                }
            } else {
                getList(query, sections)
            }

        }

    }
suspend fun getList(query: String="" ,sections:String="")
{
    var sectionQuery:String = ""
    if(sections.length>0) {
        sectionQuery = sections.replace(",", "|")
        if(sectionQuery.substring(0,1).equals("|")) {
            sectionQuery = sectionQuery.substring(1)
        }
        _SelectedSectionsList.value = (sectionQuery.split("|"))
        selectedSectionsList.value = _SelectedSectionsList.value;
    }
    if (query.equals("")) {
        call = lastNewsListUseCase(sections = sectionQuery)
    } else {
        call = searchInNewsListUseCase(query,sectionQuery, current_page);
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

    fun LoadMore(query: String = "",sections:String="") {
        current_page++
        currentSearchQuery = query
         viewModelScope.launch {
            _newsList.emit(NewsListState.Loading("", tmpNewsList))
             if (sections.equals("")) {
                 getSelectedSectionsUseCase().collect {
                     getLoadMoreList(query, it)
                 }
             } else {
                 getLoadMoreList(query, sections)

             }
        }

    }

   suspend fun getLoadMoreList(query: String = "",sections:String="")
    {
        var sectionQuery:String = ""
        if(sections.length>0) {
            sectionQuery = sections.replace(",", "|")
            if(sectionQuery.substring(0,1).equals("|")) {
                sectionQuery = sectionQuery.substring(1)
            }
            _SelectedSectionsList.value = (sectionQuery.split("|"))
            selectedSectionsList.value = _SelectedSectionsList.value;
        }
        if (query.equals("")) {
            call = lastNewsListUseCase(current_page,sectionQuery);
        } else {
            call = searchInNewsListUseCase(query,sectionQuery, current_page);
        }
        call?.let {

            try {
                tmpNewsList?.let { tmpedNewsList ->
                    tmpedNewsList.response.results += it.response.results
                    _newsList.emit(NewsListState.Success("", tmpedNewsList))
                }
            } catch (e: HttpException) {
                _newsList.emit(NewsListState.Error("Error!!", null))
            }
        }
        _newsList.collectLatest {
            newsList.value = it
        }
    }
}