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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kurrirapps.presentation.auth.UserData
import com.example.kurrirapps.ui.component.listPesanan
import com.example.kurrirapps.ui.navigation.Screen
import com.example.kurrirapps.ui.theme.Brown
import com.example.kurrirapps.ui.theme.yellow


@Composable
fun PesananMasuk(
    userData: UserData?,
    onNavigateToScreen:(String)->Unit
){
 Box(modifier = Modifier
     .fillMaxSize()
     .background(yellow))
    Column(
         modifier = Modifier
             .fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box (modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter){
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Brown)
                .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ){
                if (userData?.username != null){
                    Text(
                        text = userData.username,
                        color = Color.White
                    )
                }
                if(userData?.profilePictureUrl !=null){
                    AsyncImage(model = userData.profilePictureUrl,
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
        Text(text = "Pesanan Masuk",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = (30.sp),
                fontWeight = FontWeight.Bold
            ))
        Spacer(modifier = Modifier.height(40.dp))
        Column (
            modifier = Modifier.padding(10.dp)
        ){
            listPesanan(onNavigateToScreen)


        }
        Column (
            modifier = Modifier.padding(10.dp)
        ){
            listPesanan(onNavigateToScreen)


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