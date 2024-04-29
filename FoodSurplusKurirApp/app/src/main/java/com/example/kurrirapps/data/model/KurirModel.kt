package com.example.kurrirapps.data.model

import com.example.kurrirapps.logic.StatusKurir

data class KurirModel (
    val id : String = "",
    val name : String = "",
    val phoneNumber : String = "",
    val statusKurir: StatusKurir = StatusKurir.IDLE,
    val idHotel : String = ""
)