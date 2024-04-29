package com.example.kurrirapps.data

import com.example.kurrirapps.data.model.PesananModel
import com.example.kurrirapps.logic.StatusPesanan
import com.example.kurrirapps.model.DaftarKatalis
import com.example.kurrirapps.model.DaftarPesanan
import com.google.firebase.Timestamp

class DummyData {
    companion object {

        val pesanan1 = PesananModel(
            "1",
            "2",
            "22",
            "23",
            StatusPesanan.TERKONFIRMASI_ADMIN,
            Timestamp.now(),
            geolokasi_tujuan = "0.0,0.0",
            ongkir = 0f,
            catatan = ""
        )

        val pesanan2 = PesananModel(
            "1",
            "2",
            "22",
            "23",
            StatusPesanan.TERKONFIRMASI_ADMIN,
            Timestamp.now(),
            geolokasi_tujuan = "0.0,0.0",
            ongkir = 0f,
            catatan = ""
        )

        val pesanan3 = PesananModel(
            "1",
            "2",
            "22",
            "23",
            StatusPesanan.TERKONFIRMASI_ADMIN,
            Timestamp.now(),
            geolokasi_tujuan = "0.0,0.0",
            ongkir = 0f,
            catatan = ""
        )

        val dummyPesananFlow: List<PesananModel> = listOf(pesanan1, pesanan2, pesanan3)
    }
}