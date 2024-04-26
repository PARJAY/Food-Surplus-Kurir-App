package com.example.kurrirapps.presentation.kurir

sealed interface KurirEvent {
    data class GetKurir(val idKurir : String) : KurirEvent
}