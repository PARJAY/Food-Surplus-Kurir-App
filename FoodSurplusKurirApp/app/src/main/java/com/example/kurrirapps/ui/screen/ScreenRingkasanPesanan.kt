package com.example.kurrirapps.ui.screen

import androidx.compose.runtime.Composable
import com.example.kurrirapps.data.model.PesananModel

@Composable
fun ScreenRingkasanPesanan(
    selectedDetailPesanan: PesananModel
) {
//
//
//
//    var captureImageUri by remember { mutableStateOf<Uri>(Uri.EMPTY) }
//    var text by remember { mutableStateOf("") }
//
//    val cameraLauncher =
//        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
//            captureImageUri = uri
//            Log.d("KonfirmdgnFoto", "data foto : $uri")
//        }
//
//    val hotelToUserDistanceInMeter by remember { mutableFloatStateOf(0f) }
//
//    val selectedKatalisList = remember { mutableStateListOf<SelectedKatalis>() }
//
//    LaunchedEffect(Unit) {
//        Log.d("Ringkasan Pesanan Screen", "Launched?")
//        Log.d("Ringkasan Pesanan Screen", "Launched?")
//        selectedDetailPesanan.daftarKatalis.forEach { (key, value) ->
//            Log.d("Ringkasan Pesanan Screen", "Katalis key : $key || katalis value : $value")
//            val katalisModel = MyApp.appModule.katalisRepository.getKatalisById(key)
//            selectedKatalisList.add(
//                SelectedKatalis(
//                    idKatalis = katalisModel.id,
//                    quantity = value,
//                    stokKatalis = katalisModel.stok,
//                    namaKatalis = katalisModel.namaKatalis,
//                    hargaKatalis = katalisModel.hargaJual
//                )
//            )
//            Log.d("Ringkasan Pesanan Screen", "added katalisModel : $katalisModel")
//        }
//
//        Log.d("Ringkasan Pesanan Screen", "selectedKatalisList final: $selectedKatalisList")
//    }
//
//
//    Box (modifier = Modifier
//        .fillMaxSize()
//        .background(Color.Yellow)
//    ) {
//
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//            Spacer(modifier = Modifier.height(70.dp))
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ){
//                    Text(text = "Ringkasan Pesanan")
//                RingkasanPesanan(selectedKatalis = selectedKatalisList, hotelToUserDistanceInMeter)
//                }
//                Image(painter = painterResource(id = R.drawable.ic_launcher_background),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .width(250.dp)
//                        .height(250.dp),)
//                Spacer(modifier = Modifier.height(30.dp))
//                Text(text = "Konfirmasi Pesanan",
//                    style = TextStyle(
//                        fontSize = (28.sp),
//                        fontWeight = FontWeight.Bold
//                    )
//                )
//                Spacer(modifier = Modifier.width(20.dp))
//                if (captureImageUri.path?.isNotEmpty() == true)
//                {
//                    AsyncImage(model = captureImageUri, contentDescription =null,
//                        modifier = Modifier.size(250.dp))
//                }
//                Spacer(modifier = Modifier.height(40.dp))
//                SisipkanPesan()
////               Konfirmasi(onNavigateToPesananMasukScreen)
//                Button(
//                    modifier = Modifier
//                        .height(50.dp)
//                        .width(250.dp),
//                    shape = RoundedCornerShape(0.dp),
//                    onClick = {
//                       if (selectedImageUri?.path!!.isEmpty()) {
//                           Toast.makeText(context, "Select an Image", Toast.LENGTH_SHORT).show()
//                       } else {
//                           FirebaseHelper.uploadImageToFirebaseStorage(
//                               "User ${userData.userId}",
//                               selectedImageUri!!,
//                               onSuccess = {
//                                   Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
//                               },
//                               onError = {
//                                   Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
//                               }
//                           )
//
//                           onNavigateToPesananMasukScreen()
//                       }
//                    },
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color.Green,
//                        contentColor = Color.Black,
//                    ),
//                ) {
//                    Text(text = "Konfirmasi Pesanan",
//                        style = TextStyle(
//                            fontSize = (20.sp),
//                            fontWeight = FontWeight.Bold
//                        )
//                    )
//
//                }
//
//        }
//
//    fun Context.createImagefile(): File {
//            val timeStamp = SimpleDateFormat("YYYY_MM_dd_HH:mm:ss").format(Date())
//            val imageFileName = "JPEG_" + timeStamp + "_"
//            val image = File.createTempFile(
//                imageFileName,
//                ".jpg,",
//                externalCacheDir
//
//            )
//            return image
//        }
//
//    }

}










//@Preview(showBackground = true)
//@Composable
//fun screenAkhirPreview() {
//    KurrirAppsTheme {
//        screenAkhir(
//            userData= UserData(),
//            onNavigateToPesananMasukScreen = {},
//            onNavigateToScreen={}
//        )
//    }
//}