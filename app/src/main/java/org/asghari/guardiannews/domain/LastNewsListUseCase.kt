package org.asghari.guardiannews.domain


import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.asghari.guardiannews.data.models.NewsList
import org.asghari.guardiannews.data.models.Result
import javax.inject.Inject

class LastNewsListUseCase @Inject constructor(private  val  guardianNewsRepository: GuardianNewsRepository) {
    suspend fun Call(page:Int=1): NewsList =
        guardianNewsRepository.getLastNewsList(page,"starRating,thumbnail,short-url")

}