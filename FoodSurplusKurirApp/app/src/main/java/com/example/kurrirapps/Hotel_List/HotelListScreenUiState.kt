package com.example.kurrirapps.Hotel_List

import com.example.kurrirapps.data.model.HotelModel


data class HotelListScreenUiState(
    val isLoading: Boolean = false,
    val hotel: List<HotelModel> = emptyList(),
    val errorMessage: String? = null,
    val hotelToBeUpdated: HotelModel? = null,
    val isShowDeleteUserDialog: Boolean = false,
)