package com.example.kurrirapps.presentation.kurir

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kurrirapps.data.repository.KurirRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class KurirViewModel(private val kurirRepositoryImpl: KurirRepositoryImpl) : ViewModel() {

    private val _state: MutableStateFlow<KurirState> =
        MutableStateFlow(KurirState())
    val state: StateFlow<KurirState> = _state.asStateFlow()

    private fun setState(newState: KurirState) {
        _state.value = newState
    }

    fun updateDataKurir (selectedKuriirlId : String, idHotel : String ) {
        viewModelScope.launch {
            setState(_state.value.copy(isLoading = true))
            try {
                kurirRepositoryImpl.updateIdHotelKurir(kurirId = selectedKuriirlId, fieldToUpdate = "idHotel", newValue = idHotel)
                setState(_state.value.copy(isLoading = false))
            } catch (e: Exception) {
                setState(_state.value.copy(isLoading = false, errorMessage = e.localizedMessage))
            }
        }
    }
}