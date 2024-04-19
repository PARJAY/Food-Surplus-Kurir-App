package com.example.kurrirapps.presentation.pesanan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kurrirapps.model.Pesanan
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PesananViewModel (
    private val pesananRepository: PesananRepository
    ): ViewModel() {
        private val _state = MutableStateFlow(PesananState())

        private val _pesanan = pesananRepository.getAllPesanan()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

        val state = combine(_state, _pesanan) { state, pesanan ->
            state.copy(
                pesananListState = pesanan
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
}