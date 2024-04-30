package com.example.kurrirapps.presentation.pesanan

import com.example.kurrirapps.data.model.PesananModel

data class PesananListScreenUiState(
    val isLoading: Boolean = false,
    val Pesanan: List<PesananModel> = emptyList(),
    val errorMessage: String? = null,
    val PesananToBeUpdated: PesananModel? = null,
    val isShowDeleteUserDialog: Boolean = false,
    val pesananList: List<PesananModel> = emptyList(),
    val transaksiListState: List<PesananModel> = emptyList(),
)

data class SelectedPesanan(
    val idKatalis: String,
    var quantity: Int,
    var namaKatalis : String = "",
    var hargaKatalis : Float = 0F,
    var stokKatalis : Int,
    var alamatTujuan : String,
    var jarak_user_dan_hotel : Int,
    var onkir : Float
)
