package com.example.kurrirapps.data.model

import com.example.kurrirapps.logic.StatusPesanan
import com.example.kurrirapps.model.DaftarPesanan

data class PesananModel(

    var id_pesanan: String = "",
    var id_user: String = "",
    var id_hotel: String="",
    var id_kurir: String="",
    var id_list_daftar_pesanan: DaftarPesanan = DaftarPesanan(),
    var total_harga: Float = 0f,
    var status_pesanan: StatusPesanan,
    var catatan : String= ""
)

