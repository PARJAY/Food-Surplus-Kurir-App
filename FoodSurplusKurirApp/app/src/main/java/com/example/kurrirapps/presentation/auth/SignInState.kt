package com.example.kurrirapps.presentation.auth

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInErrorMessage: String? = null
)
