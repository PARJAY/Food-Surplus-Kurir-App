package com.example.kurrirapps.tools

import android.net.Uri
import android.util.Log
import com.example.dummyfirebaseauth.MyApp
import com.example.kurrirapps.data.model.KurirModel
import com.example.kurrirapps.logic.StatusKurir
import com.google.firebase.firestore.DocumentSnapshot

class FirebaseHelper {

    companion object {

        fun fetchSnapshotToKurirModel(queryDocumentSnapshot: DocumentSnapshot): KurirModel {

            val statusKurirQS = queryDocumentSnapshot.getString("statusKurir") ?: ""
            var statusKurir: StatusKurir = StatusKurir.OFF

            if (statusKurirQS == "IDLE") statusKurir = StatusKurir.IDLE
            else if (statusKurirQS == "OFF") statusKurir = StatusKurir.OFF
            else if (statusKurirQS == "MENGIRIM") statusKurir = StatusKurir.MENGIRIM

            return KurirModel(
                id = queryDocumentSnapshot.id,
                name = queryDocumentSnapshot.getString("name") ?: "",
                phoneNumber = queryDocumentSnapshot.getString("phoneNumber") ?: "",
                idhotel = queryDocumentSnapshot.getString("idHotel") ?: "",
                statusKurir = statusKurir
            )
        }

    fun uploadImageToFirebaseStorage(
        kurirIdForFileReference : String,
        file: Uri,
        onSuccess: (String) -> Unit,
        onError: (Exception) -> Unit
    ) {
        val fileName = file.path?.substringAfterLast("/")

        MyApp.appModule.storage.getReference("$kurirIdForFileReference/$fileName").putFile(file).addOnSuccessListener { snapshot ->
            snapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener { uri ->
                Log.d("Firebase Helper: ", "upload Success : $uri")
                onSuccess(uri.toString())
            }?.addOnFailureListener { exception ->
                Log.d("Firebase Helper: ", "upload Failed : $exception")
                onError(exception)
            }
        }.addOnFailureListener { exception ->
            Log.d("Firebase Helper: ", "Error : $exception")
            onError(exception)
        }
    }
    }
}
