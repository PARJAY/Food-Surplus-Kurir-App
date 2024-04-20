package com.example.kurrirapps.presentation.pesanan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kurrirapps.data.model.PesananModel
import com.example.kurrirapps.logic.StatusPesanan
import com.example.kurrirapps.model.DaftarPesanan
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PesananViewModel (
    private val pesananRepository: PesananRepository
    ): ViewModel() {
        private val _state = MutableStateFlow(PesananState())

        private val _pesanan = pesananRepository.getAllPesanan()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private fun setState(newState: PesananState) {
        _state.value = newState
    }
    private fun setEffect(builder: () -> PesananSideEffect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }
    private val _effect: Channel<PesananSideEffect> = Channel()
    val effect = _effect.receiveAsFlow()

        val state = combine(_state, _pesanan) { state, transaksi ->
            state.copy(
                transaksiListState = transaksi
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PesananState())

        fun onEvent(event: PesananEvent) {
            when (event) {
                is PesananEvent.DeletePesanan -> {
                    viewModelScope.launch {
                        pesananRepository.deletePesanan(event.pesanan)
                    }
                }

                is PesananEvent.UpdatePesanan -> {
                    viewModelScope.launch {
                        pesananRepository.updatePesanan(event.pesanan)
                    }
                }

                is PesananEvent.ReadPesanan -> {
                    viewModelScope.launch {
                        pesananRepository.readPesanan(event.pesanan)
                    }
                }

                is PesananEvent.CreatePesanan -> {
                    viewModelScope.launch {
                        pesananRepository.insertPesanan(event.pesanan)

                    }
                }
            }
        }

    fun editcatatan(idPesanan: String, catatan:String) {
        viewModelScope.launch {
            setState(_state.value.copy(isLoading = true))

            try {
                pesananRepository.editcatatan(
                    pesananId = idPesanan,
                    PesananModel(
                        status_pesanan = StatusPesanan.SUDAH_SAMPAI,
                        catatan = catatan
                    )
                )
                setState(_state.value.copy(isLoading = false))
                setEffect { PesananSideEffect.ShowSnackBarMessage(message = "Tambah Catatan successfully") }
            } catch (e: Exception) {
                setState(_state.value.copy(isLoading = false, errorMessage = e.localizedMessage))
                setEffect { PesananSideEffect.ShowSnackBarMessage(e.message ?: "Tambah Catatan GAGAl ") }
            }
        }
    }
}