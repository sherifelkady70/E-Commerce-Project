package com.route.domain.common

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Fail(val error : Throwable) : Resource<Nothing>()
    data class ServerError(val message : ServerErrorDC) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}