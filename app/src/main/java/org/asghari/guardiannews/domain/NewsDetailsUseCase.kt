package org.asghari.guardiannews.domain

import com.test.moviehub.domain.base.UseCase
import com.test.moviehub.domain.exceptions.IErrorHandler
import org.asghari.guardiannews.data.models.NewsList
import javax.inject.Inject

class NewsDetailsUseCase  @Inject constructor(
    private  val  guardianNewsRepository: GuardianNewsRepository,
    errorHandler: IErrorHandler
    ):UseCase<NewsList,String>(errorHandler) {
    override suspend fun run(newsId: String?): NewsList =
        guardianNewsRepository.getNewsById(newsId!!,"bodyText,thumbnail")
}