package org.asghari.guardiannews.domain


import androidx.paging.PagingData
import com.test.moviehub.domain.base.UseCase
import com.test.moviehub.domain.exceptions.IErrorHandler
import kotlinx.coroutines.flow.Flow
import org.asghari.guardiannews.data.models.NewsList
import org.asghari.guardiannews.data.models.Result
import javax.inject.Inject

class LastNewsListUseCase @Inject constructor(private  val  guardianNewsRepository: GuardianNewsRepository,
                                              errorHandler: IErrorHandler
): UseCase<NewsList, Int>(errorHandler) {
    override suspend fun run(page: Int?): NewsList {
        var mypage: Int = 1
       page?.let {
           if(it >= 1) {
           mypage = it;
       }
    }
     return   guardianNewsRepository.getLastNewsList(mypage, "starRating,thumbnail,short-url")
    }



}