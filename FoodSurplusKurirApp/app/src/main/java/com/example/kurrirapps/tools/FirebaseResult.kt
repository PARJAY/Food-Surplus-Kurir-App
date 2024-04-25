package com.example.kurrirapps.tools


sealed class FirebaseResult<out T : Any> {
    data class Success<out T: Any>(val data: T) : FirebaseResult<T>()
    data class Failure(val exception: Exception) : FirebaseResult<Nothing>()
}