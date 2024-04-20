package com.example.kurrirapps.data.repository

import android.util.Log
import com.example.kurrirapps.data.common.KURIR_COLLECTION
import com.example.kurrirapps.data.model.KurirModel
import com.example.kurrirapps.tools.FirebaseHelper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


class KurirRepositoryImpl(private val db : FirebaseFirestore) {

    suspend fun getKurirById(kurirId: String): KurirModel {
        Log.d("repo : ", "$KURIR_COLLECTION / $kurirId")
        val documentSnapshot = db.collection(KURIR_COLLECTION).document(kurirId).get().await()
        Log.d("Kurir Repo", "${documentSnapshot.data}")

        return FirebaseHelper.fetchSnapshotToKurirModel(documentSnapshot)
    }

    suspend fun addOrUpdateCustomer(customerId: String, newCustomer: KurirModel) {
        db.collection(KURIR_COLLECTION).document(customerId).set(newCustomer).await()
    }

}