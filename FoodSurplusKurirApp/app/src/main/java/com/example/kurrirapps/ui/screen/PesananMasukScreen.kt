package com.example.kurrirapps.ui.screen

import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.dummyfirebaseauth.MyApp
import com.example.kurrirapps.data.model.PesananModel
import com.example.kurrirapps.logic.StatusPesanan
import com.example.kurrirapps.presentation.auth.UserData
import com.example.kurrirapps.presentation.pesanan.PesananEvent
import com.example.kurrirapps.ui.component.ListPesanan
import com.example.kurrirapps.ui.navigation.Screen
import com.example.kurrirapps.ui.theme.Brown


@Composable
fun PesananMasuk(
    userData: UserData?,
    onNavigateToScreen: (String) -> Unit,
    onSetSelectedDetailPesanan: (PesananModel) -> Unit,
) {
    val contex = LocalContext.current

    val pesananHotel = remember { mutableStateListOf<PesananModel>() }

    LaunchedEffect (Unit) {
        MyApp.appModule.pesananRepository.getLivePesananHotel (
            errorCallback = {
                Toast.makeText(contex, "error : $it", Toast.LENGTH_LONG).show()
                Log.d("PesananMasukScreen", "error : $it")
            },
            addDataCallback = {
                pesananHotel.add(it)
                Log.d("PesananMasukScreen", "added to screen : $it")
            },
            updateDataCallback = { updatedData ->
                val index = pesananHotel.indexOfFirst { it.id == updatedData.id }
                if (index != -1) pesananHotel[index] = updatedData
                Log.d("PesananMasukScreen", "updated to screen : $updatedData")
            },
            deleteDataCallback = { documentId: String ->
                pesananHotel.removeAll { it.id == documentId }
                Log.d("PesananMasukScreen", "deleted from screen : id = $documentId")
            }
        )
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Row (
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

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Brown)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                if (userData?.username != null)
                    Text(
                        text = userData.username,
                        color = Color.White
                    )

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

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = "Pesanan Masuk",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = (30.sp),
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(40.dp))
            }
        }

        items(pesananHotel) {pesanan ->
            if (pesanan.status_pesanan == StatusPesanan.TERKONFIRMASI_ADMIN) {
                ListPesanan(
                    onNavigateToScreen = {
                        onSetSelectedDetailPesanan(pesanan)
                        onNavigateToScreen(it)},
                    pesananModel = pesanan
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