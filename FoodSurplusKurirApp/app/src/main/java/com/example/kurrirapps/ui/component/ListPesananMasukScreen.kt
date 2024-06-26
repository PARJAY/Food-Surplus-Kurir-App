package com.example.kurrirapps.ui.component

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dummyfirebaseauth.MyApp
import com.example.kurrirapps.data.model.HotelModel
import com.example.kurrirapps.data.model.PesananDisplayedNameModel
import com.example.kurrirapps.data.model.PesananModel
import com.example.kurrirapps.logic.StatusPesanan
import com.example.kurrirapps.ui.navigation.Screen
import com.example.kurrirapps.ui.theme.HijauMuda
import com.example.kurrirapps.ui.theme.HijauTua
import com.example.kurrirapps.ui.theme.KurrirAppsTheme
import com.google.firebase.Timestamp

@Composable
fun ListPesananMasukScreen(
    onNavigateToScreen:(String)->Unit,
    pesananModel: PesananModel,
    idHotel : String
) {
    val jarakDlmKm = pesananModel.jarak_user_dan_hotel/1000

    val displayedCustomerName = remember { mutableStateOf("") }
    val displayedHotelName = remember { mutableStateOf("") }
    val displayedHotelAdress = remember { mutableStateOf("") }
    val displayedNameList = remember { mutableStateOf(PesananDisplayedNameModel()) }

    LaunchedEffect (Unit) {

        Log.d("Cek Nama Hotel","hotelModel.idHotel : $idHotel")
        Log.d("Cek Nama Customer","pesananModel.id_user ${pesananModel.id_user}")

        displayedNameList.value.namaCustomer = MyApp.appModule.customerRepositoryImpl.getCustomerById(pesananModel.id_user).name
        displayedNameList.value.namaHotel = MyApp.appModule.hotelRepository.getHotelById(idHotel).name

        displayedCustomerName.value = MyApp.appModule.customerRepositoryImpl.getCustomerById(pesananModel.id_user).name
        displayedHotelName.value = MyApp.appModule.hotelRepository.getHotelById(idHotel).name
        displayedHotelAdress.value = MyApp.appModule.hotelRepository.getHotelById(idHotel).alamat

        Log.d("Cek Isi Hotel","DisplayedNamedList Hotel ${displayedNameList.value.namaHotel}")
        Log.d("Cek Isi Customer","DisplayedNamedList Customer ${displayedNameList.value.namaCustomer}")
    }

    Button(
        modifier = Modifier.padding(8.dp),
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = HijauMuda,
            contentColor = Color.White
        )
    ) {
        Column {
            Text(
                text = displayedCustomerName.value,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            // TODO : REVISI kontent BELAKANGAN
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = displayedHotelName.value)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Alamat : ")
            Text(text =  displayedHotelAdress.value)
            Spacer(modifier = Modifier.height(5.dp))
            Log.d("Mana yang lebih cepat","nampilin, atau load data?")
            Text(text = "Jarak Hotel Ke User :" + " " + jarakDlmKm + "km")
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Rp." + pesananModel.ongkir)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { onNavigateToScreen(Screen.KonfirmDgnFoto.route) },
                    colors = ButtonDefaults.buttonColors(containerColor = HijauTua),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Ambil",
                        style = TextStyle(
                            fontSize = (18.sp),
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun KonfirmasiPreview(){
    KurrirAppsTheme {
        ListPesananMasukScreen(
            onNavigateToScreen = {},
            pesananModel = PesananModel(
                id = "id? ngapain nampilin Id?",
                id_hotel = "id hotel buat nyari nama hotelnya",
                status_pesanan = StatusPesanan.TERKONFIRMASI_ADMIN,
                waktu_pesanan_dibuat = Timestamp.now()
            ),
            idHotel = ""
        )
    }
}