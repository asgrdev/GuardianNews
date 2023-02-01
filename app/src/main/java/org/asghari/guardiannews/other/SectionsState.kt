package org.asghari.guardiannews.other

 import org.asghari.guardiannews.data.models.sections.Sections

sealed class SectionsState{
    data class Success(val message:String,val data: Sections):SectionsState()
    data class Error(val message:String,val data: Sections? =null):SectionsState()
    data class Loading(val message:String,val data: Sections?):SectionsState()


}
