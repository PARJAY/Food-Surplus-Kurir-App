package com.example.kurrirapps.ui.screen

import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.example.dummyfirebaseauth.MyApp
import com.example.kurrirapps.data.model.PesananModel
import com.example.kurrirapps.logic.StatusPesanan
import com.example.kurrirapps.presentation.auth.UserData
import com.example.kurrirapps.presentation.pesanan.PesananRepository
import com.example.kurrirapps.presentation.pesanan.PesananViewModel
import com.example.kurrirapps.presentation.pesanan.SelectedPesanan
import com.example.kurrirapps.tools.FirebaseHelper
import com.example.kurrirapps.tools.Utils.Companion.showToast
import com.example.kurrirapps.ui.component.RingkasanPesanan
import com.example.kurrirapps.ui.navigation.Screen
import com.example.kurrirapps.ui.theme.Brown
import com.example.kurrirapps.ui.theme.HijauMuda
import com.example.kurrirapps.ui.theme.HijauTua
import com.example.kurrirapps.ui.theme.Krem
import com.example.kurrirapps.ui.theme.KurrirAppsTheme
import com.example.kurrirapps.ui.theme.White
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Objects


@Composable
fun KonfirmDgnFoto(
    userData: UserData,
    onNavigateToPesananMasukScreen:()->Unit,
    pesananViewModel: PesananViewModel,
    selectedDetailPesanan: PesananModel
) {
    val context = LocalContext.current
    val file = context.createImagefile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        context.packageName + ".provider", file
    )

    var captureImageUri by remember { mutableStateOf<Uri>(Uri.EMPTY) }
    var catatan by remember { mutableStateOf("") }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            captureImageUri = uri
            Log.d("KonfirmdgnFoto", "data foto : $uri")
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {

    }

    val hotelToUserDistanceInMeter by remember { mutableFloatStateOf(0f) }

    val selectedPesananList = remember { mutableStateListOf<SelectedPesanan>() }

    LaunchedEffect(Unit) {
        Log.d("Ringkasan Pesanan Screen", "Launched?")
        
        selectedDetailPesanan.daftarKatalis.forEach { (key, value) ->
            Log.d("Ringkasan Pesanan Screen", "Katalis key : $key || katalis value : $value")
            val katalisModel = MyApp.appModule.katalisRepository.getKatalisById(key)
            selectedPesananList.add(
                SelectedPesanan(
                    idKatalis = katalisModel.id,
                    quantity = value,
                    stokKatalis = katalisModel.stok,
                    namaKatalis = katalisModel.namaKatalis,
                    hargaKatalis = katalisModel.hargaJual,
                    jarak_user_dan_hotel = selectedDetailPesanan.jarak_user_dan_hotel,
                    onkir = selectedDetailPesanan.ongkir,
                    alamatTujuan = selectedDetailPesanan.alamatTujuan
                )
            )
            Log.d("Ringkasan Pesanan Screen", "added katalisModel : $katalisModel")
        }

        Log.d("Ringkasan Pesanan Screen", "selectedKatalisList final: $selectedPesananList")
    }

    LazyColumn(
        modifier = Modifier.background(Krem),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{
            Column (
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "Ringkasan Pesanan",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(30.dp))
                RingkasanPesanan(
                    selectedKatalis = selectedPesananList,
                    hotelToUserDistanceInMeter
                )

                Spacer(modifier = Modifier.height(30.dp))

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
                            .height(200.dp)
                            .width(200.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = HijauMuda,
                            contentColor = White
                        )
                    ) {
                        Text(
                            text = "Foto konfirmasi Pesanan Sudah Sampai",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = (20.sp),
                                textAlign = TextAlign.Center
                                )
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
                else {
                    Spacer(modifier = Modifier.height(20.dp))
                    AsyncImage(
                        model = captureImageUri,
                        contentDescription = null,
                        modifier = Modifier
                            .size(300.dp)
                            .clickable {
                                val permissionCheckResult =
                                    ContextCompat.checkSelfPermission(
                                        context,
                                        android.Manifest.permission.CAMERA
                                    )

                                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED)
                                    cameraLauncher.launch(uri)
                                else permissionLauncher.launch(android.Manifest.permission.CAMERA)
                            }
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Sisipkan Pesan",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = Color.Black
                    )

                    OutlinedTextField(
                        value = catatan,
                        onValueChange = { newText ->
                            catatan = newText
                        },
//                                shape = RoundedCornerShape(20.dp),
                        label = { Text("Catatan") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    )

                }
                Spacer(modifier = Modifier.height(15.dp))
                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {
                        if (captureImageUri.path!!.isEmpty())
                            showToast(context, "Select an Image")
                        else {
                            FirebaseHelper.uploadImageToFirebaseStorage(
                                "Kurir ${userData.userId}",
                                captureImageUri,
                                onSuccess = { showToast(context, it) },
                                onError = { showToast(context, "$it") }
                            )
                            pesananViewModel.beriCatatanPadaPesanan(
                                idPesanan = selectedDetailPesanan.id,
                                catatan = catatan
                            )

                            pesananViewModel.ubahStatusPesanan(
                                idPesanan = selectedDetailPesanan.id,
                            )
                            pesananViewModel.tambahkanFotoBuktiKurir(
                                idPesanan = selectedDetailPesanan.id,
                                buktiFoto = captureImageUri.lastPathSegment.toString()
                            )

                            Log.d("cek catatan", catatan)
                            onNavigateToPesananMasukScreen()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = HijauTua,
                        contentColor = Color.White,
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




@Preview(showBackground = true)
@Composable
fun KonfirmDgnFotoPreview() {
    KurrirAppsTheme {
        KonfirmDgnFoto(
            userData =  UserData(),
            onNavigateToPesananMasukScreen={},
        pesananViewModel = PesananViewModel(pesananRepository = PesananRepository(db = FirebaseFirestore.getInstance())),
            selectedDetailPesanan = PesananModel(
                id = "sdas",
                id_user = "weqw",
                id_kurir = "dasdas",
                id_hotel = "asdasd",
                status_pesanan = StatusPesanan.TERKONFIRMASI_ADMIN,
                waktu_pesanan_dibuat = Timestamp.now(),
                )
        )
    }
}
