package com.example.kurrirapps.presentation.auth

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val userData: UserData? = UserData(),
    val signInErrorMessage: String? = null
)
