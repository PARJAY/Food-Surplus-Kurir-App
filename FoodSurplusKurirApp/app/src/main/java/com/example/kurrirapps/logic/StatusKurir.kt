package com.example.kurrirapps.logic

enum class StatusKurir(val value: Int) {
    IDLE(1),
    OFF(2),
    MENGIRIM(3);

    companion object {
        fun fromInt(value: Int) = entries.first { it.value == value }
    }
}