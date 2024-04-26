package com.example.kurrirapps.presentation.pesanan

import com.example.kurrirapps.data.model.PesananModel

sealed interface PesananEvent {

    data object ReadPesanan : PesananEvent
    data object UpdatePesanan : PesananEvent

}
