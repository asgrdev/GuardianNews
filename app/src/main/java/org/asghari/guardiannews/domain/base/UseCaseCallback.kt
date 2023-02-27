package org.asghari.guardiannews.domain.base

import org.asghari.guardiannews.domain.exceptions.ErrorModel

interface UseCaseCallback<Type> {

    fun onSuccess(result: Type)

    fun onError(errorModel: ErrorModel?)
}