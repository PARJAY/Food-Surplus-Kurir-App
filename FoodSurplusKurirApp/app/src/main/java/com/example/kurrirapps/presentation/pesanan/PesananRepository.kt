package com.example.kurrirapps.presentation.pesanan

import android.util.Log
import com.example.kurrirapps.data.common.INTERNET_ISSUE
import com.example.kurrirapps.data.common.PESANAN_COLLECTION
import com.example.kurrirapps.data.model.PesananModel
import com.example.kurrirapps.data.model.SetPesananStatusModel
import com.example.kurrirapps.tools.FirebaseHelper.Companion.fetchSnapshotToPesanan
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.tasks.await

class PesananRepository(private val db : FirebaseFirestore) {

    private var listenerRegistration: ListenerRegistration? = null
    fun getLivePesananHotel(
        errorCallback: (Exception) -> Unit,
        addDataCallback: (PesananModel) -> Unit,
        updateDataCallback: (PesananModel) -> Unit,
        deleteDataCallback: (documentId: String) -> Unit
    ) {
        listenerRegistration = db.collection(PESANAN_COLLECTION).addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                errorCallback(IllegalStateException(INTERNET_ISSUE))
                return@addSnapshotListener
            }

            snapshot!!.documentChanges.forEach { change ->
                val pesananModel = fetchSnapshotToPesanan(change.document)
                when (change.type) {
                    DocumentChange.Type.ADDED -> addDataCallback(pesananModel)
                    DocumentChange.Type.MODIFIED -> updateDataCallback(pesananModel)
                    DocumentChange.Type.REMOVED -> deleteDataCallback(change.document.id)
                }

                Log.d("Pesanan Repo: ", "Data In -> ${change.type} - ${change.document}")
            }
        }
    }

    fun detachListener() {
        listenerRegistration?.remove()
        listenerRegistration = null
    }

    suspend fun editcatatan(pesananId: String, catatan: SetPesananStatusModel) {
        db.collection(PESANAN_COLLECTION).document(pesananId).set(catatan).await()
    }

    suspend fun getAllHotelPesananByHotelId(pesananId: String, catatan: PesananModel) {
        db.collection(PESANAN_COLLECTION).document(pesananId).set(catatan).await()
    }
}