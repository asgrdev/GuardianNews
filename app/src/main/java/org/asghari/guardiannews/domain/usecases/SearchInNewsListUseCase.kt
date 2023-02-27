package org.asghari.guardiannews.domain.usecases


import org.asghari.guardiannews.data.models.news.NewsList
import org.asghari.guardiannews.domain.base.UseCaseCallback
import org.asghari.guardiannews.domain.exceptions.IErrorHandler
import org.asghari.guardiannews.domain.repositories.GuardianNewsRepository
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class SearchInNewsListUseCase @Inject constructor(private  val  guardianNewsRepository: GuardianNewsRepository  ,private val errorHandler: IErrorHandler)  {
    operator suspend fun invoke(query:String,  sections:String,page: Int?,
                                onResult: (UseCaseCallback<NewsList>)?) {
        var mypage: Int = 1
       page?.let {
           if(it >= 1) {
           mypage = it;
       }
    }
        try {
            val result = guardianNewsRepository.getNewsListBYQuery(query,sections,mypage, "starRating,thumbnail,short-url")
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