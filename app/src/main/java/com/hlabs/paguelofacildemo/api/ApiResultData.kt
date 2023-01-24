package com.hlabs.paguelofacildemo.api


sealed class ApiResultData<T> {
    data class Success<T>(val data: T) : ApiResultData<T>()
    data class NotFound<T>(val data: T) : ApiResultData<T>()
    data class Error<T>(val error: Throwable) : ApiResultData<T>()
}

inline fun <T, R> ApiResultData<T>.getResult(
    success: (ApiResultData.Success<T>) -> R,
    error: (ApiResultData.Error<T>) -> R
): R = if (this is ApiResultData.Success) success(this) else error(this as ApiResultData.Error)

inline fun <T> ApiResultData<T>.onSuccess(
    block: (T) -> Unit
): ApiResultData<T> = if (this is ApiResultData.Success) also { block(data) } else this

inline fun <T> ApiResultData<T>.onNotFound(
    block: (T) -> Unit
): ApiResultData<T> = if (this is ApiResultData.Success) also { block(data) } else this

inline fun <T> ApiResultData<T>.onError(
    block: (Throwable) -> Unit
): ApiResultData<T> = if (this is ApiResultData.Error) also { block(error) } else this
