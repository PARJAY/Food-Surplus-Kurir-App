package com.example.kurrirapps.presentation.pesanan

import com.example.kurrirapps.data.common.PESANAN_COLLECTION
import com.example.kurrirapps.data.model.PesananModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.await

class PesananRepository(private val db : FirebaseFirestore) {
    fun getAllPesanan (): Flow<List<PesananModel>> = flowOf()

    suspend fun editcatatan(pesananId: String, catatan: PesananModel) {
        db.collection(PESANAN_COLLECTION).document(pesananId).set(catatan).await()
    }
}