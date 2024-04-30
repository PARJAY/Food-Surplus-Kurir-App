package com.example.kurrirapps.data.repository

import android.util.Log
import com.example.kurrirapps.data.common.CUSTOMER_COLLECTION
import com.example.kurrirapps.data.common.KATALIS_COLLECTION
import com.example.kurrirapps.data.model.CustomerModel
import com.example.kurrirapps.data.model.KatalisModel
import com.example.kurrirapps.tools.FirebaseHelper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class CustomerRepositoryImpl(private val db : FirebaseFirestore){
    suspend fun getCustomerById(customerId: String): CustomerModel {
        val documentSnapshot = db.collection(CUSTOMER_COLLECTION).document(customerId).get().await()
        Log.d("Get Customer By Id", "${documentSnapshot.data}")

        return FirebaseHelper.fetchSnapshotToCustomerModel(documentSnapshot)
    }
}