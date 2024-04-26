package com.example.kurrirapps.data.repository

import android.util.Log
import com.example.kurrirapps.data.common.KURIR_COLLECTION
import com.example.kurrirapps.data.model.KurirModel
import com.example.kurrirapps.tools.FirebaseHelper
import com.example.kurrirapps.tools.FirebaseResult
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.tasks.await


class KurirRepositoryImpl(private val db : FirebaseFirestore): KurirRepository {

    private var listenerRegistration : ListenerRegistration? = null

//    override suspend fun getKurirList(callback: (FirebaseResult<List<KurirModel>>) -> Unit, idCustomer : String) {
//        val pesananModelSnapshots = mutableListOf<KurirModel>()
//
//        listenerRegistration = db.collection(KURIR_COLLECTION).addSnapshotListener { snapshot, exception ->
//            if (exception != null) {
//                callback(FirebaseResult.Failure(IllegalStateException(INTERNET_ISSUE)))
//                return@addSnapshotListener
//            }
//
//            snapshot!!.documentChanges.forEach { change ->
//                val kurirModel = FirebaseHelper.fetchSnapshotToKurirModel(change.document)
//
//                    when (change.type) {
//                        DocumentChange.Type.ADDED -> addDataCallback(hotelModel)
//                        DocumentChange.Type.MODIFIED -> updateDataCallback(hotelModel)
//                        DocumentChange.Type.REMOVED -> deleteDataCallback(hotelModel.id)
//                    }
//                Log.d("REPOSITORY: ", "Data In -> ${change.type} - ${change.document}")
//            }
//
//            callback(FirebaseResult.Success(pesananModelSnapshots))
//        }
//    }

    suspend fun getKurirById(kurirId: String): KurirModel {
        Log.d("repo : ", "$KURIR_COLLECTION / $kurirId")
        val documentSnapshot = db.collection(KURIR_COLLECTION).document(kurirId).get().await()
        Log.d("Kurir Repo", "${documentSnapshot.data}")

        return FirebaseHelper.fetchSnapshotToKurirModel(documentSnapshot)
    }

    suspend fun addOrUpdateCustomer(customerId: String, newCustomer: KurirModel) {
        db.collection(KURIR_COLLECTION).document(customerId).set(newCustomer).await()
    }

    suspend fun updateIdHotelKurir(kurirId: String, fieldToUpdate: String, newValue: String) {
        db.collection(KURIR_COLLECTION).document(kurirId).update(fieldToUpdate, newValue)
    }

    // dipake kalau nggak pengen nerima data realtime lagi
    fun detachListener() {
        listenerRegistration?.remove()
        listenerRegistration = null
    }

    override suspend fun getPesananList(
        callback: (FirebaseResult<List<KurirModel>>) -> Unit,
        idCustomer: String
    ) {
        TODO("Not yet implemented")
    }

}