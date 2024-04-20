package com.example.kurrirapps.ui.screen

import android.content.Context
import android.net.Uri
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.kurrirapps.presentation.auth.UserData
import com.example.kurrirapps.ui.component.Konfirmasi
import com.example.kurrirapps.ui.component.SisipkanPesan
import com.example.kurrirapps.ui.component.TopBar
import com.example.kurrirapps.ui.navigation.Screen
import com.example.kurrirapps.ui.theme.Brown
import com.example.kurrirapps.ui.theme.yellow
import java.io.File
import java.util.Date

@Composable
fun screenAkhir(
    userData: UserData?,
    onNavigateToScreen:(String)->Unit,
    onNavigateToPesananMasukScreen:()->Unit)
{
    var captureImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }


   Box (modifier = Modifier
       .fillMaxSize()
       .background(yellow)){
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
       Column(
           modifier = Modifier
               .fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally
       ) {

           Spacer(modifier = Modifier.height(70.dp))
           Column(
               modifier = Modifier
                   .fillMaxWidth(),
               horizontalAlignment = Alignment.CenterHorizontally
           ){
               if (captureImageUri.path?.isNotEmpty() == true)


               {
                 AsyncImage(model = captureImageUri, contentDescription =null,
                     modifier = Modifier.size(250.dp))
               }
               Spacer(modifier = Modifier.height(40.dp))
               SisipkanPesan()
//               Konfirmasi(onNavigateToPesananMasukScreen)

           }
           fun Context.createImagefile(): File {
               val timeStamp = java.text.SimpleDateFormat("YYYY_MM_dd_HH:mm:ss").format(Date())
               val imageFileName = "JPEG_" + timeStamp + "_"
               val image = File.createTempFile(
                   imageFileName,
                   ".jpg,",
                   externalCacheDir

               )
               return image

           }

       }

   }

}








//@Preview(showBackground = true)
//@Composable
//fun sisipkanPesanPreview(){
//    KurrirAppsTheme {
//        screenAkhir(onNavigateToPesananMasukScreen = {})
//    }
//}