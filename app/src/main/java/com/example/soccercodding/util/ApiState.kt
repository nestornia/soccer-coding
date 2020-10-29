package com.example.soccercodding.util


sealed class ApiState<out T: Any> {
    data class Success<out T: Any>(val data: T) : ApiState<T>()
    data class Error(val exception: Exception) : ApiState<Nothing>()
    object Loading : ApiState<Nothing>()
}

// handling here of the api state