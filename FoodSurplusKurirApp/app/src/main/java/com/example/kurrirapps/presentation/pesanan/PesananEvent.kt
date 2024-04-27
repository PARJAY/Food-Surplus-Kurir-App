package com.example.kurrirapps.presentation.pesanan

import com.example.kurrirapps.logic.StatusPesanan

sealed interface PesananEvent {

    data object ReadPesanan : PesananEvent
    data class UpdatePesanan(val idPesanan: String, val statusPesanan: StatusPesanan, val catatan : String) : PesananEvent

}
