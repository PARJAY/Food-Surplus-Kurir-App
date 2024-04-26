package com.example.kurrirapps.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kurrirapps.Hotel_List.HotelListScreenEffects
import com.example.kurrirapps.Hotel_List.HotelListScreenUiState
import com.example.kurrirapps.data.model.HotelModel
import com.example.kurrirapps.data.model.PesananModel
import com.example.kurrirapps.logic.StatusPesanan
import com.example.kurrirapps.model.DaftarPesanan
import com.example.kurrirapps.presentation.auth.UserData
import com.example.kurrirapps.presentation.pesanan.PesananEvent
import com.example.kurrirapps.presentation.pesanan.PesananListScreenUiState
import com.example.kurrirapps.ui.component.listPesanan
import com.example.kurrirapps.ui.navigation.Screen
import com.example.kurrirapps.ui.theme.Brown
import com.example.kurrirapps.ui.theme.yellow
import kotlinx.coroutines.flow.Flow


@Composable
fun PesananMasuk(
    userData: UserData?,
    onNavigateToScreen:(String)->Unit,
    pesananListScreenUiState : PesananListScreenUiState,
    onPesananMasukEvent : (PesananEvent) -> Unit,
    hotelListScreenUiState: HotelListScreenUiState,
    hotelListScreenEffectFlow : Flow<HotelListScreenEffects>,
){

    val HotelModels = remember {
        mutableStateListOf<HotelModel>()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        items(HotelModels){Hotel ->
            Column {
                Text(text = "Test Data")
                Text(text = Hotel.id)
                Text(text = Hotel.name)
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Test")
            }
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(yellow)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(Brown)
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Absolute.SpaceBetween
                    ) {
                        if (userData?.username != null) {
                            Text(
                                text = userData.username,
                                color = Color.White
                            )
                        }
                        if (userData?.profilePictureUrl != null) {
                            AsyncImage(
                                model = userData.profilePictureUrl,
                                contentDescription = "Profile Picture",
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                                    .clickable {
                                        onNavigateToScreen(Screen.ScreenProfile.route)
                                    },
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                }
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = "Pesanan Masuk",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = (30.sp),
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(40.dp))
//
            }
            Column {
                listPesanan(
                    onNavigateToScreen = onNavigateToScreen,
                    pesananModel = PesananModel(
                        "sup ayam",
                        "23456",
                        "3478",
                        "000099888",
                        DaftarPesanan(
                            "adsasd",
                            "adasdas",
                            "sdasdasd",
                            12
                        ),
                        100000f,
                        StatusPesanan.SUDAH_SAMPAI
                    ),
                    onPesananMasukEvent
                )
            }
        }



    }
}




//@Preview(showBackground = true)
//@Composable
//fun PesananPreview() {
//    KurrirAppsTheme {
//        PesananMasuk(onNavigateToScreen = {},
//            )
//    }
//}

//onClick = {navController.navigate("klikFoto")},