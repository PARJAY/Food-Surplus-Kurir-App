package com.example.kurrirapps.presentation.pesanan

import com.example.kurrirapps.model.Pesanan

data class PesananState (
    val pesananListState: List<Pesanan> = emptyList()
)