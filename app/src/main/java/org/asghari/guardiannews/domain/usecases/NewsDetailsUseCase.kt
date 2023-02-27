package org.asghari.guardiannews.domain.usecases


import org.asghari.guardiannews.data.models.news.NewsList
import org.asghari.guardiannews.domain.base.UseCase
import org.asghari.guardiannews.domain.exceptions.IErrorHandler
import org.asghari.guardiannews.domain.repositories.GuardianNewsRepository
import javax.inject.Inject

class NewsDetailsUseCase  @Inject constructor(
    private  val  guardianNewsRepository: GuardianNewsRepository,
    errorHandler: IErrorHandler
    ): UseCase<NewsList, String>(errorHandler) {
    override suspend fun run(newsId: String?): NewsList =
        guardianNewsRepository.getNewsById(newsId!!,"body,byline,bylineHtml,bodyText,thumbnail")
}