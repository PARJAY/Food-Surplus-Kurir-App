package com.example.kurrirapps.presentation.Hotel_List

sealed class HotelListScreenEffects {
    data class ShowSnackBarMessage(val message: String) : HotelListScreenEffects()
}