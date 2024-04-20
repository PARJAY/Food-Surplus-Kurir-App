package com.example.kurrirapps.presentation.pesanan

sealed class PesananSideEffect {
    data class ShowSnackBarMessage(val message: String) : PesananSideEffect()
}