package com.example.kurrirapps.presentation.Hotel_List

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kurrirapps.data.model.HotelModel
import com.example.kurrirapps.data.repository.HotelRepositoryImpl
import com.example.kurrirapps.tools.FirebaseResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HotelListScreenViewModel(private val hotelRepositoryImpl: HotelRepositoryImpl) : ViewModel() {

    private val _state: MutableStateFlow<HotelListScreenUiState> =
        MutableStateFlow(HotelListScreenUiState())
    val state: StateFlow<HotelListScreenUiState> = _state.asStateFlow()

    private val _effect: Channel<HotelListScreenEffects> = Channel()
    val effect = _effect.receiveAsFlow()

    init { onEvent(HotelEvent.GetHotel) }

    private fun setEffect(builder: () -> HotelListScreenEffects) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    private fun setState(newState: HotelListScreenUiState) {
        _state.value = newState
    }

    fun onEvent(event: HotelEvent) {
        when (event) {
            is HotelEvent.GetHotel -> getHotel()
            is HotelEvent.DeleteHotel -> deleteHotel(event.hotelId)
            is HotelEvent.AddHotel -> addHotel(event.newHotel)
            is HotelEvent.UpdateHotel -> updateHotel(event.updatedHotel)
        }
    }

    private fun getHotel() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true) // Update loading state

            hotelRepositoryImpl.getHotel { firebaseResult ->
                when (firebaseResult) {
                    is FirebaseResult.Failure -> {
                        setState(_state.value.copy(isLoading = false))
                        Log.d("VIEWMODEL: ", "${firebaseResult.exception.message}")
                        setEffect {
                            HotelListScreenEffects.ShowSnackBarMessage(
                                firebaseResult.exception.message ?: "Error fetching users"
                            )
                        }
                    }
                    is FirebaseResult.Success -> {
                        Log.d("VIEWMODEL: ", "${firebaseResult.data}")
                        _state.value = _state.value.copy(isLoading = false, hotel = firebaseResult.data)
                        setEffect { HotelListScreenEffects.ShowSnackBarMessage(message = "User list data loaded successfully") }
                    }
                }
            }
        }
    }

    private fun deleteHotel(hotelId: String) {
        viewModelScope.launch {
            setState(_state.value.copy(isLoading = true))

            try {
                hotelRepositoryImpl.deleteHotel(hotelId)
                setState(_state.value.copy(isLoading = false))
                setEffect { HotelListScreenEffects.ShowSnackBarMessage(message = "Hotel deleted successfully") }
            } catch (e: Exception) {
                setState(_state.value.copy(isLoading = false, errorMessage = e.localizedMessage))
                setEffect {
                    HotelListScreenEffects.ShowSnackBarMessage(
                        e.message ?: "Error fetching users"
                    )
                }
            }
        }
    }

    private fun updateHotel(updatedHotel: HotelModel) {
        viewModelScope.launch {
            setState(_state.value.copy(isLoading = true))

            try {
                hotelRepositoryImpl.updateHotel(updatedHotel.idHotel, updatedHotel)
                setState(_state.value.copy(isLoading = false))
                setEffect { HotelListScreenEffects.ShowSnackBarMessage(message = "Hotel updated successfully") }
            } catch (e: Exception) {
                setState(_state.value.copy(isLoading = false, errorMessage = e.localizedMessage))
                setEffect {
                    HotelListScreenEffects.ShowSnackBarMessage(
                        e.message ?: "Error fetching hotel"
                    )
                }
            }
        }
    }

    private fun addHotel(newHotel: HotelModel) {
        viewModelScope.launch {
            setState(_state.value.copy(isLoading = true))

            try {
                hotelRepositoryImpl.addHotel(newHotel)
                setState(_state.value.copy(isLoading = false))
                setEffect { HotelListScreenEffects.ShowSnackBarMessage(message = "Hotel added successfully") }
            } catch (e: Exception) {
                setState(_state.value.copy(isLoading = false, errorMessage = e.localizedMessage))
                setEffect {
                    HotelListScreenEffects.ShowSnackBarMessage(
                        e.message ?: "Error fetching hotel"
                    )
                }
            }
        }
    }
}