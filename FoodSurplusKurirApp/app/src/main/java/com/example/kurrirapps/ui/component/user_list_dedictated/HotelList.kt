package com.example.kurrirapps.ui.component.user_list_dedictated

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kurrirapps.data.model.HotelModel

@Composable
fun HotelItem(
    Hotel: HotelModel,
    onUpdateAction : (HotelModel) -> Unit,
    onDeleteAction : (String) -> Unit
) {
    val hotelModel by remember {
        mutableStateOf(Hotel)
    }

    Column (modifier = Modifier.padding(top = 8.dp)) {
        Text(text = Hotel.idHotel)
        Text(text = Hotel.name)
        Text(text = Hotel.phoneNumber)

        Button(onClick = { onUpdateAction(hotelModel) }) {
            Text(text = "Edit")
        }

        Button(onClick = { onDeleteAction(hotelModel.idHotel) }) {
            Text(text = "Delete")
        }
    }
}