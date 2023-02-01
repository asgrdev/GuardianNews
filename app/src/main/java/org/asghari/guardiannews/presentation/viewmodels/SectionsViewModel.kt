package org.asghari.guardiannews.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.asghari.guardiannews.domain.usecases.GetAllSectionsUseCase
import org.asghari.guardiannews.domain.usecases.GetSectionByIdUseCase
import org.asghari.guardiannews.other.NewsListState
import org.asghari.guardiannews.other.SectionsState
import javax.inject.Inject

@HiltViewModel
class SectionsViewModel @Inject constructor(
    private val  getAllSectionsUseCase: GetAllSectionsUseCase
,private val getSectionByIdUseCase: GetSectionByIdUseCase):ViewModel() {
    private val _SectionsList:MutableSharedFlow<SectionsState>  =  MutableStateFlow<SectionsState>(
        SectionsState.Loading("", null))
    var sectionList = mutableStateOf<SectionsState>(SectionsState.Loading("",null))

    init {
        getSectionsList()
    }
    private fun getSectionsList()
    {

        viewModelScope.launch {
            _SectionsList.emit(SectionsState.Loading("",null))
            try {
                val sections = getAllSectionsUseCase()
                _SectionsList.emit(SectionsState.Success("Success",sections))
            }
            catch (e:Exception)
            {
                _SectionsList.emit(SectionsState.Error("${e.message}",null))
            }
            _SectionsList.collectLatest {
                sectionList.value = it
            }
        }
    }
}