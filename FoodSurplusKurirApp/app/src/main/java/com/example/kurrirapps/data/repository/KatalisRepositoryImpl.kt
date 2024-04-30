package com.example.kurrirapps.data.repository

import android.util.Log
import com.example.kurrirapps.data.common.INTERNET_ISSUE
import com.example.kurrirapps.data.common.KATALIS_COLLECTION
import com.example.kurrirapps.data.model.KatalisModel
import com.example.kurrirapps.tools.FirebaseHelper.Companion.fetchSnapshotToKatalisModel
import com.example.kurrirapps.tools.FirebaseResult
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.tasks.await

class KatalisRepositoryImpl(private val db : FirebaseFirestore) : KatalisRepository {

    private var listenerRegistration : ListenerRegistration? = null

    override suspend fun getKatalisList(callback: (FirebaseResult<List<KatalisModel>>) -> Unit) {
        val katalisModelSnapshots = mutableListOf<KatalisModel>()

        listenerRegistration = db.collection(KATALIS_COLLECTION).addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                callback(FirebaseResult.Failure(IllegalStateException(INTERNET_ISSUE)))
                return@addSnapshotListener
            }

            snapshot!!.documentChanges.forEach { change ->
                val katalisModel = fetchSnapshotToKatalisModel(change.document)
                when (change.type) {
                    DocumentChange.Type.ADDED -> katalisModelSnapshots.add(katalisModel)
                    DocumentChange.Type.MODIFIED -> {
                        val index = katalisModelSnapshots.indexOfFirst { it.id == change.document.id }
                        if (index != -1) katalisModelSnapshots[index] = katalisModel
                    }

                    DocumentChange.Type.REMOVED -> katalisModelSnapshots.removeAll { it.id == change.document.id }
                }
                Log.d("REPOSITORY: ", "Data In -> ${change.type} - ${change.document}")
            }

            callback(FirebaseResult.Success(katalisModelSnapshots))
        }
    }

    suspend fun getKatalisById(katalisId: String): KatalisModel {
        val documentSnapshot = db.collection(KATALIS_COLLECTION).document(katalisId).get().await()
        Log.d("Get Katalis By Id", "${documentSnapshot.data}")

        return fetchSnapshotToKatalisModel(documentSnapshot)
    }


    // dipake kalau nggak pengen nerima data realtime lagi
    fun detachListener() {
        listenerRegistration?.remove()
        listenerRegistration = null
    }
}