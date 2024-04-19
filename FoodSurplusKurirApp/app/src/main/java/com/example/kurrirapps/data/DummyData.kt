package com.example.kurrirapps.data

import com.example.kurrirapps.logic.StatusPesanan
import com.example.kurrirapps.model.DaftarPesanan
import com.example.kurrirapps.model.Pesanan

class DummyData {
    companion object {

        val pesanan1 = Pesanan(1, 2, 22, 23, DaftarPesanan(12,23,45,10), 100f,StatusPesanan.SUDAH_DIPESAN )

        val pesanan2 = Pesanan(1, 2, 22, 23, DaftarPesanan(12,23,45,10), 100f,StatusPesanan.SUDAH_DIPESAN )

        val pesanan3 = Pesanan(1, 2, 22, 23, DaftarPesanan(12,23,45,10), 100f,StatusPesanan.SUDAH_DIPESAN )


        val dummyPesananFlow: List<Pesanan> = listOf(pesanan1, pesanan2, pesanan3)
    }
}