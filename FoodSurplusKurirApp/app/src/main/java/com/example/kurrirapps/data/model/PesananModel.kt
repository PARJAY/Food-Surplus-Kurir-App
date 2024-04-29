package com.example.kurrirapps.data.model

import com.example.kurrirapps.logic.StatusPesanan
import com.google.firebase.Timestamp

data class PesananModel(
    var id: String = "",
    var id_user: String = "",
    var id_hotel: String="",
    var id_kurir: String="",
    var status_pesanan: StatusPesanan,
    var waktu_pesanan_dibuat : Timestamp?,
    var daftarKatalis: Map<String, Int> = emptyMap(),
    var geolokasi_tujuan : String = "",
    var ongkir : Float = 0f,
    var catatan : String= "",
    var alamatTujuan : String= ""
)

data class SetPesananStatusModel(
    var status_pesanan: StatusPesanan,
    var catatan : String= ""
)