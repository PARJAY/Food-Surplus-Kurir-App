package com.example.kurrirapps.data.model

import com.example.kurrirapps.logic.StatusPesanan
import com.example.kurrirapps.model.DaftarPesanan

data class PesananModel(

    val id_pesanan: Int = 0,
    val id_user: Int = 0,
    val id_hotel: Int = 0,
    var id_kurir: Int = 0,
    var id_list_daftar_pesanan: DaftarPesanan,
    var total_harga: Float = 0f,
    var status_pesanan: StatusPesanan

)

