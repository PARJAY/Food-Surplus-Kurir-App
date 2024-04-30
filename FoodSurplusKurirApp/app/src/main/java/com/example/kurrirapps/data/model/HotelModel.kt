package com.example.kurrirapps.data.model

data class HotelModel (
    var idHotel : String = "",
    var name : String = "",
    var phoneNumber : String = "",
    var email : String = "",
    var alamat : String = "",
    var geolocation : String = "",
    var listIdKatalis : List<String> = listOf(),
    var statusHotel : String = ""
)