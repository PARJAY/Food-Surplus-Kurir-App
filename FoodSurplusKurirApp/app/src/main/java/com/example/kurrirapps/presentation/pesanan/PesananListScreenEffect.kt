package com.example.kurrirapps.presentation.pesanan


sealed class PesananListScreenSideEffect {
    data class ShowSnackBarMessage(val message: String) : PesananListScreenSideEffect()
}