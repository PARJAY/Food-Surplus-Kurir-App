package com.example.kurrirapps.presentation.kurir

import com.example.kurrirapps.data.model.KurirModel


data class KurirState(
    val kurirState: KurirModel = KurirModel(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)