package com.example.kurrirapps.model

import com.example.kurrirapps.logic.StatusPesanan

data class Pesanan(
    var id_pesanan: Int,
    var id_user: Int,
    var id_hotel: Int,
    var id_kurir: Int,
    var id_list_daftar_pesanan: DaftarPesanan,
    var total_harga: Float,
    var status_pesanan: StatusPesanan
) {
    fun GetUserbyId(){

    }
    fun GetHotelbyId(){

    }
    fun GetKurirbyId(){

    }
}