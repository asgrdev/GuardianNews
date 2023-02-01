package org.asghari.guardiannews.domain.usecases


import org.asghari.guardiannews.data.models.news.NewsList
import org.asghari.guardiannews.domain.repositories.GuardianNewsRepository
import javax.inject.Inject

class SearchInNewsListUseCase @Inject constructor(private  val  guardianNewsRepository: GuardianNewsRepository){
    operator suspend fun invoke(query:String,page: Int?): NewsList {
        var mypage: Int = 1
       page?.let {
           if(it >= 1) {
           mypage = it;
       }
    }
     return   guardianNewsRepository.getNewsListBYQuery(query,mypage, "starRating,thumbnail,short-url")
    }



}