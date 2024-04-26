package com.example.kurrirapps.data.repository

import android.util.Log
import com.example.kurrirapps.data.common.INTERNET_ISSUE
import com.example.kurrirapps.data.common.LIVEDATA_TRAINING
import com.example.kurrirapps.data.model.HotelModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import fetchSnapshotToHotel

class HotelRepository(val db : FirebaseFirestore) {
    private var listenerRegistration : ListenerRegistration? = null

    fun getLiveDataHotel(
        errorCallback: (Exception) -> Unit,
        addDataCallback: (HotelModel) -> Unit,
        updateDataCallback: (HotelModel) -> Unit,
        deleteDataCallback: (documentId : String) -> Unit
    ) {


        listenerRegistration = db.collection(LIVEDATA_TRAINING).addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                errorCallback(IllegalStateException(INTERNET_ISSUE))
                return@addSnapshotListener
            }

            snapshot!!.documentChanges.forEach { change ->
                val hotelModel = fetchSnapshotToHotel(change.document)
                when (change.type) {
                    DocumentChange.Type.ADDED -> addDataCallback(hotelModel)
                    DocumentChange.Type.MODIFIED -> updateDataCallback(hotelModel)
                    DocumentChange.Type.REMOVED -> deleteDataCallback(hotelModel.id)
                }
                Log.d("LDES Repo: ", "Data In -> ${change.type} - ${change.document}")
            }
        }
    }

    fun detachListener() {
        listenerRegistration?.remove()
        listenerRegistration = null
    }
}