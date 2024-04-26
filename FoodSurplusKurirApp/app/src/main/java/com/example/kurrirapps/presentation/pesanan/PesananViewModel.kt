package com.example.kurrirapps.presentation.pesanan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kurrirapps.data.model.PesananModel
import com.example.kurrirapps.logic.StatusPesanan
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
        private val _state = MutableStateFlow(PesananListScreenUiState())

        private val _pesanan = pesananRepository.getAllPesanan()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private fun setState(newState: PesananListScreenUiState) {
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
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PesananListScreenUiState())

        fun onEvent(event: PesananEvent) {
            when (event) {
                is PesananEvent.UpdatePesanan -> {
                    viewModelScope.launch {
                        pesananRepository.getAllPesanan()
                    }
                }
                is PesananEvent.ReadPesanan -> {
                    viewModelScope.launch {
                        pesananRepository.getAllPesanan()
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