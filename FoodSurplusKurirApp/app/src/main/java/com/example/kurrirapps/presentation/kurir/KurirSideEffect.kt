package com.example.kurrirapps.presentation.kurir

sealed class KurirSideEffect {
    data class ShowSnackBarMessage(val message: String) : KurirSideEffect()
}