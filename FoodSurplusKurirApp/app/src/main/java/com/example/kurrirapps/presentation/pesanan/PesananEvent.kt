package com.example.kurrirapps.presentation.pesanan

import com.example.kurrirapps.model.Pesanan

sealed interface PesananEvent {

    data class CreatePesanan(val pesanan: Pesanan): PesananEvent

    data class UpdatePesanan(val pesanan: Pesanan): PesananEvent

    data class ReadPesanan(val pesanan: Pesanan): PesananEvent

    data class DeletePesanan(val pesanan: Pesanan): PesananEvent
}