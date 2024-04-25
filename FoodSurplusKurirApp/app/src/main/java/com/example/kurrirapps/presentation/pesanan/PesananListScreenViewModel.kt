import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kurrirapps.data.repository.PesananRepositoryImpl
import com.example.kurrirapps.presentation.pesanan.PesananEvent
import com.example.kurrirapps.presentation.pesanan.PesananListScreenSideEffect
import com.example.kurrirapps.presentation.pesanan.PesananListScreenUiState
import com.example.kurrirapps.tools.FirebaseResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class PesananListScreenViewModel(
    private val pesananRepository: PesananRepositoryImpl
) : ViewModel() {

    private val _state: MutableStateFlow<PesananListScreenUiState> =
        MutableStateFlow(PesananListScreenUiState())
    val state: StateFlow<PesananListScreenUiState> = _state.asStateFlow()

    private val _effect: Channel<PesananListScreenSideEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        onEvent(PesananEvent.ReadPesanan)
    }

    private fun setEffect(builder: () -> PesananListScreenSideEffect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    private fun setState(newState: PesananListScreenUiState) {
        _state.value = newState
    }

    fun onEvent(event: PesananEvent) {
        when (event) {
            is PesananEvent.ReadPesanan -> getPesanan()
            is PesananEvent.UpdatePesanan -> getPesanan()
        }
    }

    private fun getPesanan() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true) // Update loading state

            pesananRepository.getPesananList { firebaseResult ->
                if (firebaseResult is FirebaseResult.Failure) {
                    setState(_state.value.copy(isLoading = false))
                    Log.d("VIEWMODEL: ", "error - ${firebaseResult.exception.message}")
                    setEffect {
                        PesananListScreenSideEffect.ShowSnackBarMessage(
                            firebaseResult.exception.message ?: "Error fetching users"
                        )
                    }
                } else if (firebaseResult is FirebaseResult.Success) {
                    Log.d("VIEWMODEL: ", "sucess - ${firebaseResult.data}")
                    _state.value =
                        _state.value.copy(isLoading = false, pesananList = firebaseResult.data)
                    setEffect { PesananListScreenSideEffect.ShowSnackBarMessage(message = "User list data loaded successfully") }
                }
            }
        }
    }
}