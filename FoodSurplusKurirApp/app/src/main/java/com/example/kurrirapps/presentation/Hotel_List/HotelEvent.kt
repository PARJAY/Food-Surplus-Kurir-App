package com.example.kurrirapps.presentation.Hotel_List

import com.example.kurrirapps.data.model.HotelModel

sealed class HotelEvent {
    object GetHotel : HotelEvent()
    data class DeleteHotel(val hotelId: String) : HotelEvent()
    data class UpdateHotel(val updatedHotel: HotelModel) : HotelEvent()
    data class AddHotel(val newHotel: HotelModel) : HotelEvent()
}