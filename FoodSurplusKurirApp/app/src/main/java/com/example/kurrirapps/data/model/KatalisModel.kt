package com.example.kurrirapps.data.model

data class KatalisModel(
    val id: String = "",
    val idHotel: String = "",
    val namaKatalis: String = "",
    val imageLink: String = "",
    val stok: Int = 0,
    val komposisi: List<String> = listOf(),
    val hargaAwal: Float = 0f,
    val hargaJual: Float = 0f,
    val porsiJual: String = "",
    val lokasiTujuan: String = ""
)