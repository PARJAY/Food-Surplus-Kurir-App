package com.example.kurrirapps.presentation.kurir

import com.example.kurrirapps.data.model.KurirModel


data class KurirState(
    val kurirState: List<KurirModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)