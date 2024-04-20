package com.example.kurrirapps.ui.screen

import android.content.Context
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberImagePainter
import com.example.kurrirapps.R
import com.example.kurrirapps.presentation.auth.UserData
import java.io.File
import java.net.URI
import java.util.Date
import java.util.Objects

@Composable
fun imageCaptureFormCamera(
    userData: UserData?,
    onNavigateToScreen:(String)->Unit,
    onNavigateToPesananMasukScreen:()->Unit)
 {

    val context = LocalContext.current
    val file = context.createImagefile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        context.packageName + ".provider", file
    )

    var captureImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }



    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            captureImageUri = uri
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Akses Diizinkan", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Tidak Diizinkan", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Button(
            onClick = {
            val permissionCheckResult =
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)

            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED)
            {
                cameraLauncher.launch(uri)
            }
            else
            {
                permissionLauncher.launch(android.Manifest.permission.CAMERA)
            }


        },
            modifier = Modifier
                .height(250.dp)
                .width(250.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(text = "Foto konfirmasi Pesanan Sudah Sampai",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = (20.sp)
                )
            )

        }

    }

//    if (captureImageUri.path?.isNotEmpty() == true)
//
//
//    {
//        Image(
//            modifier = Modifier
//                .padding(16.dp, 8.dp),
//
//            painter = rememberImagePainter(captureImageUri),
//            contentDescription = null)
//    }


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