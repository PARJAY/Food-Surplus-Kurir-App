package com.example.kurrirapps.data.repository

import android.util.Log
import com.example.kurrirapps.data.common.INTERNET_ISSUE
import com.example.kurrirapps.data.common.PESANAN_COLLECTION
import com.example.kurrirapps.data.model.PesananModel
import com.example.kurrirapps.tools.FirebaseHelper
import com.example.kurrirapps.tools.FirebaseHelper.Companion.fetchSnapshotToPesanan
import com.example.kurrirapps.tools.FirebaseResult
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.tasks.await


class PesananRepositoryImpl(private val db : FirebaseFirestore) : RepositoryPesanan {

    private var listenerRegistration : ListenerRegistration? = null

    override suspend fun getPesananList(callback: (FirebaseResult<List<PesananModel>>) -> Unit) {
        val pesananModelSnapshots = mutableListOf<PesananModel>()

        listenerRegistration = db.collection(PESANAN_COLLECTION).addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                callback(FirebaseResult.Failure(IllegalStateException(INTERNET_ISSUE)))
                return@addSnapshotListener
            }

            snapshot!!.documentChanges.forEach { change ->
                val pesananModel = fetchSnapshotToPesanan(change.document)
                when (change.type) {
                    DocumentChange.Type.ADDED -> pesananModelSnapshots.add(pesananModel)
                    DocumentChange.Type.MODIFIED -> {
                        val index = pesananModelSnapshots.indexOfFirst { it.id_pesanan == change.document.id }
                        if (index != -1) pesananModelSnapshots[index] = pesananModel
                    }

                    DocumentChange.Type.REMOVED -> pesananModelSnapshots.removeAll { it.id_pesanan== change.document.id }
                }
                Log.d("REPOSITORY: ", "Data In -> ${change.type} - ${change.document}")
            }

            callback(FirebaseResult.Success(pesananModelSnapshots))
        }
    }


    suspend fun getPesananById(pesananId: String): PesananModel {
        val documentSnapshot = db.collection(PESANAN_COLLECTION).document().get().await()
        Log.d("Get Pesanan By Id", "${documentSnapshot.data}")

        return FirebaseHelper.fetchSnapshotToPesanan(documentSnapshot)
    }

    suspend fun addOrUpdatePesanan(pesananId: String, newStok: PesananModel) {
        db.collection(PESANAN_COLLECTION).document().set(newStok).await()
    }


    // dipake kalau nggak pengen nerima data realtime lagi
    fun detachListener() {
        listenerRegistration?.remove()
        listenerRegistration = null
    }
}