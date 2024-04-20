package com.example.kurrirapps.data

import com.example.kurrirapps.data.model.PesananModel
import com.example.kurrirapps.logic.StatusPesanan
import com.example.kurrirapps.model.DaftarPesanan

class DummyData {
    companion object {

        val pesanan1 = PesananModel("1", 2, 22, 23, DaftarPesanan("12","23","45",10), 100f,StatusPesanan.SUDAH_DIPESAN )

        val pesanan2 = PesananModel("1", 2, 22, 23, DaftarPesanan("12","23","45",10), 100f,StatusPesanan.SUDAH_DIPESAN )

        val pesanan3 = PesananModel("1", 2, 22, 23, DaftarPesanan("12","23","45",10), 100f,StatusPesanan.SUDAH_DIPESAN )


        val dummyPesananFlow: List<PesananModel> = listOf(pesanan1, pesanan2, pesanan3)
    }
}