package com.example.kurrirapps.data.repository

import com.example.kurrirapps.data.common.PESANAN_COLLECTION
import com.example.kurrirapps.data.model.PesananModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class PesananRepositoryImpl(private val db : FirebaseFirestore) {

    suspend fun getUserById(userId: String): PesananModel? {
        val documentSnapshot = db.collection(PESANAN_COLLECTION).document(userId).get().await()
        return documentSnapshot.toObject(PesananModel::class.java)
    }

}