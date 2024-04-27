package com.example.kurrirapps.presentation.pesanan

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kurrirapps.data.model.SetPesananStatusModel
import com.example.kurrirapps.logic.StatusPesanan
import kotlinx.coroutines.launch

class PesananViewModel (
    private val pesananRepository: PesananRepository
): ViewModel() {

    fun onEvent(event: PesananEvent) {
        when (event) {
            is PesananEvent.UpdatePesanan -> {
                viewModelScope.launch {
                    setPesananStatus(event.idPesanan, event.statusPesanan, event.catatan)
                }
            }
            is PesananEvent.ReadPesanan -> {
            }
        }
    }

    fun setPesananStatus(idPesanan: String, statusPesanan: StatusPesanan, catatan:String) {
        viewModelScope.launch {
            try {
                pesananRepository.editcatatan(
                    pesananId = idPesanan,
                    SetPesananStatusModel(
                        status_pesanan = statusPesanan,
                        catatan = catatan
                    )
                )
            } catch (e: Exception) {
                Log.d("Viewmodel", "editcatatan exception : $e")
            }
        }
    }
}