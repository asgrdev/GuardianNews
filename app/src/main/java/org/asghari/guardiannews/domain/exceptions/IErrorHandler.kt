package org.asghari.guardiannews.domain.exceptions

interface IErrorHandler {
    fun handleException(throwable: Throwable?): ErrorModel
}