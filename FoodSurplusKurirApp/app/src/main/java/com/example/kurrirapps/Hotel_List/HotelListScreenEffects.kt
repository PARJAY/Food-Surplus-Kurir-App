package com.example.kurrirapps.Hotel_List

sealed class HotelListScreenEffects {
    data class ShowSnackBarMessage(val message: String) : HotelListScreenEffects()
}