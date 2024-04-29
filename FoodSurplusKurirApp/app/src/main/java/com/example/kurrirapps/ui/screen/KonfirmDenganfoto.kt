package com.example.kurrirapps.ui.screen

import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.example.kurrirapps.data.model.PesananModel
import com.example.kurrirapps.logic.StatusPesanan
import com.example.kurrirapps.model.DaftarPesanan
import com.example.kurrirapps.presentation.auth.UserData
import com.example.kurrirapps.presentation.pesanan.PesananViewModel
import com.example.kurrirapps.tools.FirebaseHelper
import com.example.kurrirapps.ui.component.SisipkanPesan
import com.example.kurrirapps.ui.theme.Brown
import com.example.kurrirapps.ui.theme.KurrirAppsTheme
import com.example.kurrirapps.ui.theme.yellow
import java.util.Objects


@Composable
fun KonfirmDgnFoto(
    userData: UserData,
    onNavigateToPesananMasukScreen:()->Unit,
    pesananViewModel: PesananViewModel
) {
    val context = LocalContext.current
    val file = context.createImagefile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        context.packageName + ".provider", file
    )

    var captureImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    var text by remember { mutableStateOf("") }


    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            captureImageUri = uri
            Log.d("KonfirmdgnFoto", "data foto : ${uri}")
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {}
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(yellow)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
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

            }

        }
        Spacer(modifier = Modifier.height(70.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (captureImageUri == Uri.EMPTY) {
                Button(
                    onClick = {
                        val permissionCheckResult =
                            ContextCompat.checkSelfPermission(
                                context,
                                android.Manifest.permission.CAMERA
                            )

                        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                            cameraLauncher.launch(uri)
                        } else {
                            permissionLauncher.launch(android.Manifest.permission.CAMERA)
                        }
                    },
                    modifier = Modifier
                        .height(300.dp)
                        .width(250.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "Foto konfirmasi Pesanan Sudah Sampai",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = (20.sp),

                            )
                    )
                }
            }
            else {
                AsyncImage(
                    model = captureImageUri,
                    contentDescription = null,
                    modifier = Modifier
                        .size(400.dp)
                        .clickable {
                            val permissionCheckResult =
                                ContextCompat.checkSelfPermission(
                                    context,
                                    android.Manifest.permission.CAMERA
                                )

                            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                cameraLauncher.launch(uri)
                            } else {
                                permissionLauncher.launch(android.Manifest.permission.CAMERA)
                            }
                        }
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Sisipkan Pesan",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    TextField(
                        value = text,
                        onValueChange = { newText ->
                            text = newText
                        },
                        shape = RoundedCornerShape(20.dp),
                        label = { Text("Message") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Gray, RoundedCornerShape(20.dp)
                            )
                    )
                }

                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .width(180.dp),
                    onClick = {
                        if (captureImageUri?.path!!.isEmpty()) {
                            Toast.makeText(context, "Select an Image", Toast.LENGTH_SHORT).show()
                        } else {
                            FirebaseHelper.uploadImageToFirebaseStorage(
                                "Kurir ${userData.userId}",
                                captureImageUri!!,
                                onSuccess = {
                                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                                },
                                onError = {
                                    Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
                                }
                            )
                            pesananViewModel.editcatatan(
                                idPesanan = "3Fg8AaY6VjurgMYE5ukG",
                                text
                            )

                            onNavigateToPesananMasukScreen()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black,
                    ),
                ) {
                    Text(text = "Konfirmasi",
                        style = TextStyle(
                            fontSize = (20.sp),
                            fontWeight = FontWeight.Bold
                        )
                    )

                }
            }
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun KonfirmDgnFotoPreview() {
//    KurrirAppsTheme {
//        KonfirmDgnFoto(
//            userData =  UserData(),
//            onNavigateToPesananMasukScreen={},
//        pesananViewModel = PesananViewModel()
//        )
//    }
//}
