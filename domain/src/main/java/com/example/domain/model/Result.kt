package com.example.domain.model

sealed class Result<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Result<T>(data)
    class Failure<T>(message: String, data: T? = null) : Result<T>(data, message)
    class Loading<T>(val isLoading: Boolean = true) : Result<T>(null)
}