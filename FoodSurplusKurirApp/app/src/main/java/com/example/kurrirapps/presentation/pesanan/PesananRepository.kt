package com.example.kurrirapps.presentation.pesanan

import com.example.kurrirapps.data.DummyData
import com.example.kurrirapps.model.Pesanan
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PesananRepository {
    fun getAllPesanan(): Flow<List<Pesanan>> = flowOf(DummyData.dummyPesananFlow)

    suspend fun insertPesanan(pesanan: Pesanan) {
        insertPesanan(pesanan)
    }
    suspend fun readPesanan(pesanan: Pesanan) {
        readPesanan(pesanan)
    }

    suspend fun updatePesanan(pesanan: Pesanan) {
        updatePesanan(pesanan)
    }

    suspend fun deletePesanan(pesanan: Pesanan) {
        deletePesanan(pesanan)
    }
}