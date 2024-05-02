package com.example.kurrirapps.presentation.pesanan

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PesananViewModel (
    private val pesananRepository: PesananRepository
): ViewModel() {

    fun onEvent(event: PesananEvent) {
        when (event) {
            is PesananEvent.UpdatePesanan -> {
                viewModelScope.launch {
                    beriCatatanPadaPesanan(event.idPesanan, event.catatan)
                }
            }
            is PesananEvent.ReadPesanan -> {
            }
        }
    }

    fun beriCatatanPadaPesanan(idPesanan: String, catatan:String) {
        viewModelScope.launch {
            try {
                pesananRepository.editCatatan(
                    pesananId = idPesanan,
                    catatan = catatan,
                    fieldToUpdate = "catatan"
                )
            } catch (e: Exception) {
                Log.d("Viewmodel", "editcatatan exception : $e")
            }
        }
    }
    fun ubahStatusPesanan(idPesanan: String) {
        viewModelScope.launch {
            try {
                pesananRepository.editStatusPesanan(pesananId = idPesanan, statusBaru = "SUDAH_SAMPAI", fieldToUpdate = "status_pesanan",)
            } catch (e: Exception) {
                Log.d("Viewmodel", "editcatatan exception : $e")
            }
        }
    }
    fun tambahkanFotoBuktiKurir(idPesanan: String, buktiFoto :String) {
        viewModelScope.launch {
            try {
                pesananRepository.FotoBuktiKurir(
                    pesananId = idPesanan,
                    buktiFoto = buktiFoto,
                    fieldToUpdate = "transferProofImageLink",

                )
            } catch (e: Exception) {
                Log.d("Viewmodel", "editcatatan exception : $e")
            }
        }
    }
}