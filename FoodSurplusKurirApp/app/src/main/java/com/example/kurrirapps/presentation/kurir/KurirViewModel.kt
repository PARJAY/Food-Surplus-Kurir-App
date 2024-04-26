package com.example.kurrirapps.presentation.kurir

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kurrirapps.data.repository.KurirRepositoryImpl
import com.example.kurrirapps.tools.FirebaseResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class KurirViewModel(
    private val kurirRepositoryImpl: KurirRepositoryImpl,
    idKurir : String
) : ViewModel() {

    private val _state: MutableStateFlow<KurirState> =
        MutableStateFlow(KurirState())
    val state: StateFlow<KurirState> = _state.asStateFlow()

    private val _effect: Channel<KurirSideEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        KurirEvent.GetKurir(idKurir)
    }

    private fun setEffect(builder: () -> KurirSideEffect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    private fun setState(newState: KurirState) {
        _state.value = newState
    }

    fun onEvent(event: KurirEvent) {
        when (event) {
            is KurirEvent.GetKurir -> getPesananList(event.idKurir)
//            is HomeScreenEvent.ModifyOrder -> {
//                modifyOrder(
//                    katalisId = event.katalisId,
//                    action = event.orderAction
//                )
//            }
            else -> {}
        }
    }


    private fun getPesananList(idCustomer : String) {
        Log.d("VIEWMODEL ID CUSTOMER: ", "idCustomer - $idCustomer")

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true) // Update loading state

            kurirRepositoryImpl.getPesananList(
                callback =  { firebaseResult ->
                    if (firebaseResult is FirebaseResult.Failure) {
                        setState(_state.value.copy(isLoading = false))
                        Log.d("VIEWMODEL: ", "error - ${firebaseResult.exception.message}")
                        setEffect {
                            KurirSideEffect.ShowSnackBarMessage(
                                firebaseResult.exception.message ?: "Error fetching users"
                            )
                        }
                    } else if (firebaseResult is FirebaseResult.Success) {
                        Log.d("VIEWMODEL: ", "sucess - ${firebaseResult.data}")
                        _state.value =
                            _state.value.copy(isLoading = false, kurirState = firebaseResult.data)
                        setEffect { KurirSideEffect.ShowSnackBarMessage(message = "User list data loaded successfully") }
                    }
                },
                idCustomer = idCustomer
            )
        }
    }


    fun updateDataKurir (selectedKuriirlId : String, idHotel : String ) {
        viewModelScope.launch {
            setState(_state.value.copy(isLoading = true))

            try {
                kurirRepositoryImpl.updateIdHotelKurir(kurirId = selectedKuriirlId, fieldToUpdate = "idhotel", newValue = idHotel   )
                setState(_state.value.copy(isLoading = false))
                setEffect { KurirSideEffect.ShowSnackBarMessage(message = "Update Stok successfully") }
            } catch (e: Exception) {
                setState(_state.value.copy(isLoading = false, errorMessage = e.localizedMessage))
                setEffect { KurirSideEffect.ShowSnackBarMessage(e.message ?: "Error Stok ") }
            }
        }
    }


}