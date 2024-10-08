package com.hgshkt.domain.data

sealed class Result<T> {
    data class Error<T>(val msg: String="some error"): Result<T>()
    data class Success<T>(val value: T): Result<T>()
}