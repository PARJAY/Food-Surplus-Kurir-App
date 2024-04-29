package com.example.kurrirapps.tools

import android.net.Uri
import android.util.Log
import com.example.dummyfirebaseauth.MyApp
import com.example.kurrirapps.data.model.KatalisModel
import com.example.kurrirapps.data.model.KurirModel
import com.example.kurrirapps.data.model.PesananModel
import com.example.kurrirapps.logic.StatusKurir
import com.example.kurrirapps.logic.StatusPesanan
import com.example.kurrirapps.model.DaftarKatalis
import com.google.firebase.firestore.DocumentSnapshot

class FirebaseHelper {

    companion object {

        fun fetchSnapshotToKatalisModel(queryDocumentSnapshot: DocumentSnapshot): KatalisModel {
            val komposisi = queryDocumentSnapshot.getString("idHotel") ?: ""
            val hargaAwal = queryDocumentSnapshot.getLong("hargaAwal")?.toFloat() ?: 0.0f

            Log.d("Helper", "$komposisi - ${komposisi::class.simpleName}")
            Log.d("Helper", "$hargaAwal - ${hargaAwal::class.simpleName}")

            return KatalisModel(
                id = queryDocumentSnapshot.id,
                idHotel = queryDocumentSnapshot.getString("idHotel") ?: "",
                namaKatalis = queryDocumentSnapshot.getString("namaKatalis") ?: "",
                imageLink = queryDocumentSnapshot.getString("imageLink")?: "",
                stok = queryDocumentSnapshot.getLong("stok")?.toInt() ?: 0 ,
                komposisi = queryDocumentSnapshot.getString("komposisi")?.split(",") ?: emptyList(),
                hargaAwal = queryDocumentSnapshot.getLong("hargaAwal")?.toFloat() ?: 0.0f,
                hargaJual = queryDocumentSnapshot.getLong("hargaJual")?.toFloat() ?: 0.0f,
                porsiJual = queryDocumentSnapshot.getString("porsiJual") ?: "",
            )
        }

        fun fetchSnapshotToKurirModel(queryDocumentSnapshot: DocumentSnapshot): KurirModel {

            val statusKurirQS = queryDocumentSnapshot.getString("statusKurir") ?: ""
            var statusKurir: StatusKurir = StatusKurir.OFF

            when (statusKurirQS) {
                "IDLE" -> statusKurir = StatusKurir.IDLE
                "OFF" -> statusKurir = StatusKurir.OFF
                "MENGIRIM" -> statusKurir = StatusKurir.MENGIRIM
            }

            return KurirModel(
                id = queryDocumentSnapshot.id,
                name = queryDocumentSnapshot.getString("name") ?: "",
                phoneNumber = queryDocumentSnapshot.getString("phoneNumber") ?: "",
                idHotel = queryDocumentSnapshot.getString("idHotel") ?: "",
                statusKurir = statusKurir
            )
        }

        fun uploadImageToFirebaseStorage(
            kurirIdForFileReference: String,
            file: Uri,
            onSuccess: (String) -> Unit,
            onError: (Exception) -> Unit
        ) {
            val fileName = file.path?.substringAfterLast("/")

            MyApp.appModule.storage.getReference("$kurirIdForFileReference/$fileName").putFile(file)
                .addOnSuccessListener { snapshot ->
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

        fun fetchSnapshotToPesanan(queryDocumentSnapshot: DocumentSnapshot): PesananModel {
            val statusPesananQS = queryDocumentSnapshot.getString("status_pesanan") ?: ""
            var statusKurir : StatusPesanan = StatusPesanan.MENUNGGU_KONFIRMASI_ADMIN

            when (statusPesananQS) {
                "MENUNGGU_KONFIRMASI_ADMIN" -> statusKurir = StatusPesanan.MENUNGGU_KONFIRMASI_ADMIN
                "TIDAK_DISETUJUI_ADMIN" -> statusKurir = StatusPesanan.TIDAK_DISETUJUI_ADMIN
                "TERKONFIRMASI_ADMIN" -> statusKurir = StatusPesanan.TERKONFIRMASI_ADMIN
                "DIANTAR" -> statusKurir = StatusPesanan.DIANTAR
                "SAMPAI" -> statusKurir = StatusPesanan.SAMPAI
                "SELESA" -> statusKurir = StatusPesanan.SELESAI
            }

            val daftarKatalis = DaftarKatalis()

            (queryDocumentSnapshot.data?.get("daftarKatalis") as? Map<*, *>)?.forEach { (key, value) ->
                Log.d("ListPesananKatalis Repo", "key : $key, value : $value")
                daftarKatalis.daftarKatalis += Pair(key.toString(), value.toString().toInt())
            }

            return PesananModel(
                id = queryDocumentSnapshot.id,
                id_user = queryDocumentSnapshot.getString("id_customer") ?: "",
                id_kurir = queryDocumentSnapshot.getString("id_kurir") ?: "",
                id_hotel = queryDocumentSnapshot.getString("id_hotel") ?: "",
                status_pesanan = statusKurir,
                geolokasi_tujuan = queryDocumentSnapshot.getString("geolokasi_tujuan") ?: "",
                ongkir = queryDocumentSnapshot.getLong("ongkir")?.toFloat() ?: 0.0f,
                waktu_pesanan_dibuat = queryDocumentSnapshot.getTimestamp("waktu_pesanan_dibuat")
            )
        }
    }
}
