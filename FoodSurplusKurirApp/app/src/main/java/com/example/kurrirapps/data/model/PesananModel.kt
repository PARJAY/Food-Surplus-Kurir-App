package com.example.kurrirapps.data.model

import com.example.kurrirapps.logic.StatusPesanan
import com.example.kurrirapps.model.DaftarKatalis
import com.example.kurrirapps.model.DaftarPesanan
import com.google.firebase.Timestamp

data class PesananModel(
    var id: String = "",
    var id_user: String = "",
    var id_hotel: String="",
    var id_kurir: String="",
//    var transferProofImageLink = "",
    var status_pesanan: StatusPesanan,
    var waktu_pesanan_dibuat : Timestamp?,
    var daftar_katalis_pesanan: DaftarKatalis = DaftarKatalis(),
    var geolokasi_tujuan : String = "",
    var ongkir : Float = 0f,
    var catatan : String= ""
)

data class SetPesananStatusModel(
    var status_pesanan: StatusPesanan,
    var catatan : String= ""
)