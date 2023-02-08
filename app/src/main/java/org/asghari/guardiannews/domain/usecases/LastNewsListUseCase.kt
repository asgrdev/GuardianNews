package org.asghari.guardiannews.domain.usecases


import android.util.Log
import com.test.moviehub.domain.base.UseCase
import com.test.moviehub.domain.exceptions.IErrorHandler
import org.asghari.guardiannews.data.models.news.NewsList
import org.asghari.guardiannews.domain.repositories.GuardianNewsRepository
import javax.inject.Inject

class LastNewsListUseCase @Inject constructor(private  val  guardianNewsRepository: GuardianNewsRepository )  {
  operator suspend fun invoke(page: Int?=1, sections:String): NewsList {
        var mypage: Int = 1
       page?.let {
           if(it >= 1) {
           mypage = it;
       }
    }
     return   guardianNewsRepository.getLastNewsList(mypage,   sections,"starRating,thumbnail,short-url")
    }



}