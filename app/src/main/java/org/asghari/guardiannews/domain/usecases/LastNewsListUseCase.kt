package org.asghari.guardiannews.domain.usecases


import android.util.Log

import org.asghari.guardiannews.domain.exceptions.IErrorHandler
import kotlinx.coroutines.CancellationException

import org.asghari.guardiannews.data.models.news.NewsList
import org.asghari.guardiannews.domain.base.UseCase
import org.asghari.guardiannews.domain.base.UseCaseCallback
import org.asghari.guardiannews.domain.repositories.GuardianNewsRepository
import javax.inject.Inject

class LastNewsListUseCase @Inject constructor(private  val  guardianNewsRepository: GuardianNewsRepository
,private    val errorHandler: IErrorHandler)  {
  operator suspend fun invoke(page: Int?=1,
                              sections:String,onResult: (UseCaseCallback<NewsList>)?) {
        var mypage: Int = 1
       page?.let {
           if(it >= 1) {
           mypage = it;
       }
    }

      try {
          val result = guardianNewsRepository.getLastNewsList(
              mypage,
              sections,
              "starRating,thumbnail,short-url"
          )
          onResult?.onSuccess(result)
          println("  Response: $result")

      }catch (e: CancellationException) {
          println(" Error: $e")
          onResult?.onError(errorHandler.handleException(e))
      } catch (e: Exception) {
          println(" Error:$e cause: ${e.cause}")
          onResult?.onError(errorHandler.handleException(e))
      }

    }
}