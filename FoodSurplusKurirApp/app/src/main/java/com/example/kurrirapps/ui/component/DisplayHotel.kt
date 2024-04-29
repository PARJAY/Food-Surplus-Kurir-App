package com.example.kurrirapps.ui.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.kurrirapps.data.model.HotelModel
import com.example.kurrirapps.presentation.auth.UserData
import com.example.kurrirapps.presentation.kurir.KurirViewModel
import com.example.kurrirapps.ui.navigation.Screen

@Composable
fun DisplayHotel(
    onNavigateToScreen:(String)->Unit,
    hotelModel: HotelModel,
    kuriirViewModel: KurirViewModel,
    userData: UserData
){
    Row (
        modifier = Modifier.clickable {
            Log.d("DisplayHotelComponent", hotelModel.id)
            kuriirViewModel.updateDataKurir(
                selectedKuriirlId = userData.userId,
                idHotel = hotelModel.id,
            )
            onNavigateToScreen(Screen.PesananMasuk.route)
        },
    ) {
        Text(
            text = hotelModel.name,
            modifier = Modifier.fillMaxWidth()
        )

        Icon(
            imageVector = Icons.Default.Done,
            tint = Color.Green,
            contentDescription = "Click for done"
        )
    }
}

//@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun DisplayHotelPreview() {
//    DisplayHotel(
//        onNavigateToScreen = {},
//        hotelModel = HotelModel(),
//        kuriirViewModel = ,
//        userData = UserData()
//    )
//}