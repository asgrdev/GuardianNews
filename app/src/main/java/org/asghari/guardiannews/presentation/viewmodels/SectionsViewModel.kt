package org.asghari.guardiannews.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.asghari.guardiannews.domain.usecases.*
import org.asghari.guardiannews.other.NewsListState
import org.asghari.guardiannews.other.SectionsState
import javax.inject.Inject

@HiltViewModel
class SectionsViewModel @Inject constructor(
    private val  getAllSectionsUseCase: GetAllSectionsUseCase
,private val getSectionByIdUseCase: GetSectionByIdUseCase,private val getSelectedSectionsUseCase: GetSelectedSectionsUseCase
    ,private val saveSelectedSectionUsecase: SaveSelectedSectionsUseCase
    ,private val deleteSectionByIdUsecase: DeleteSectionByIdUsecase):ViewModel() {
    private val _SectionsList:MutableSharedFlow<SectionsState>  =  MutableStateFlow<SectionsState>(
        SectionsState.Loading("", null))
    var sectionList = mutableStateOf<SectionsState>(SectionsState.Loading("",null))
    private val _SelectedSections:MutableSharedFlow<List<String>> = MutableStateFlow<List<String>>(
        listOf()
    )
    var selectedSectionsList = mutableStateOf<List<String>>(listOf())
    init {
        getSectionsList()
        getSelectedSectionsList()
    }

    fun toggleSelectedSection(isChecked:Boolean, sectionId:String)
    {
        viewModelScope.launch {
            if (isChecked) {
                saveSelectedSectionUsecase(sectionId)
            }
            else{
                deleteSectionByIdUsecase(sectionId)
            }
        }
    }

    private fun getSectionsList() {

        viewModelScope.launch {
            _SectionsList.emit(SectionsState.Loading("", null))
            try {
                val sections = getAllSectionsUseCase()
                _SectionsList.emit(SectionsState.Success("Success", sections))
            } catch (e: Exception) {
                _SectionsList.emit(SectionsState.Error("${e.message}", null))
            }
            _SectionsList.collectLatest {
                sectionList.value = it
            }

        }
    }
    private fun getSelectedSectionsList() {
        viewModelScope.launch {
            try {
                getSelectedSectionsUseCase().collect { currentSections ->
                    val selectedList:List<String> = currentSections.split(",")
                    _SelectedSections.emit(selectedList)
                    _SelectedSections.collectLatest {
                        selectedSectionsList.value = it
                    }
                }
            } catch (e: Exception) {
                _SectionsList.emit(SectionsState.Error("${e.message}", null))
            }
        }
    }
}