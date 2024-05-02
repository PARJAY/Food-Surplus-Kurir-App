package com.example.kurrirapps.data.repository

import android.util.Log
import com.example.kurrirapps.data.common.CUSTOMER_COLLECTION
import com.example.kurrirapps.data.common.HOTEL_COLLECTION
import com.example.kurrirapps.data.common.INTERNET_ISSUE
import com.example.kurrirapps.data.model.CustomerModel
import com.example.kurrirapps.data.model.HotelModel
import com.example.kurrirapps.tools.FirebaseHelper
import com.example.kurrirapps.tools.FirebaseResult
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import fetchSnapshotToHotel
//import fetchSnapshotToHotel
import kotlinx.coroutines.tasks.await

class HotelRepositoryImpl(private val db : FirebaseFirestore) {

    private var listenerRegistration: ListenerRegistration? = null

    fun getLiveDataHotel(
        errorCallback: (Exception) -> Unit,
        addDataCallback: (HotelModel) -> Unit,
        updateDataCallback: (HotelModel) -> Unit,
        deleteDataCallback: (documentId: String) -> Unit
    ) {
        listenerRegistration =
            db.collection(HOTEL_COLLECTION).addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    errorCallback(IllegalStateException(INTERNET_ISSUE))
                    return@addSnapshotListener
                }

                snapshot!!.documentChanges.forEach { change ->
                    val hotelModel = fetchSnapshotToHotel(change.document)
                    when (change.type) {
                        DocumentChange.Type.ADDED -> addDataCallback(hotelModel)
                        DocumentChange.Type.MODIFIED -> updateDataCallback(hotelModel)
                        DocumentChange.Type.REMOVED -> deleteDataCallback(hotelModel.idHotel)
                    }
                    Log.d("LDES Repo: ", "Data In -> ${change.type} - ${change.document}")
                }
            }
    }
    suspend fun getHotel(callback: (FirebaseResult<List<HotelModel>>) -> Unit) {
        val hotelModelSnapshots = mutableListOf<HotelModel>()


    }

    // dipake kalau nggak pengen nerima data realtime lagi
    fun detachListener() {
        listenerRegistration?.remove()
        listenerRegistration = null
    }

    // kayaknya nggak bakal pernah dipake
//    suspend fun getUserById(userId: String): UserModel? {
//        val documentSnapshot = db.collection(USER_COLLECTION).document(userId).get().await()
//        return documentSnapshot.toObject(UserModel::class.java)
//    }

    suspend fun addHotel(hotel: HotelModel) {
        db.collection(HOTEL_COLLECTION).add(hotel).await()
    }

    suspend fun updateHotel(hotel: String, updatedHotel: HotelModel) {
        db.collection(HOTEL_COLLECTION).document(hotel).set(updatedHotel).await()
    }

    suspend fun deleteHotel(hotel: String) {
        db.collection(HOTEL_COLLECTION).document(hotel).delete().await()
    }


    suspend fun getHotelById(hotelId: String): HotelModel {
        val documentSnapshot = db.collection(HOTEL_COLLECTION).document(hotelId).get().await()
        Log.d("Get Hotel By Id", "${documentSnapshot.data}")

        return FirebaseHelper.fetchSnapshotToHotelModel(documentSnapshot)
    }


}