package org.asghari.guardiannews.other

sealed class ScreensRoute(){
    data class HomePage(var  route : String = "HomePage")
    data class NewsScreen(var  route: String= "NewsScreen")
    data class SectionsScreen(var  route: String= "SectionsScreen")
}