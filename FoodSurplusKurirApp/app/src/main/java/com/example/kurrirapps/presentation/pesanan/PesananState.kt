package com.example.kurrirapps.presentation.pesanan

import com.example.kurrirapps.data.model.PesananModel

data class PesananState (
    val isLoading: Boolean = false,
    val transaksiListState: List<PesananModel> = emptyList(),
    val errorMessage: String? = null,
)