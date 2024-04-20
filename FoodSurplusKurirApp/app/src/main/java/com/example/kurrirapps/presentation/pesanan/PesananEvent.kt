package com.example.kurrirapps.presentation.pesanan

import com.example.kurrirapps.data.model.PesananModel

sealed interface PesananEvent {

    data class CreatePesanan(val pesanan: PesananModel): PesananEvent

    data class UpdatePesanan(val pesanan: PesananModel): PesananEvent

    data class ReadPesanan(val pesanan: PesananModel): PesananEvent

    data class DeletePesanan(val pesanan: PesananModel): PesananEvent
}