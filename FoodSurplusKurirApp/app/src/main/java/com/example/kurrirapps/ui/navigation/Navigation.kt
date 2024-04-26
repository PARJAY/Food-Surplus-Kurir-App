package com.example.kurrirapps.ui.navigation

import ScreenViewHotelModel
import android.app.Activity.RESULT_OK
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dummyfirebaseauth.MyApp
import com.example.kurrirapps.Hotel_List.HotelListScreenViewModel
import com.example.kurrirapps.data.model.KurirModel
import com.example.kurrirapps.data.repository.KurirRepositoryImpl
import com.example.kurrirapps.feature.EnumPhotoCapture
import com.example.kurrirapps.presentation.auth.GoogleAuthClient
import com.example.kurrirapps.presentation.auth.SignInViewModel
import com.example.kurrirapps.presentation.kurir.KurirViewModel
import com.example.kurrirapps.presentation.pesanan.PesananRepository
import com.example.kurrirapps.presentation.pesanan.PesananViewModel
import com.example.kurrirapps.ui.screen.KonfirmDgnFoto
import com.example.kurrirapps.ui.screen.LoginScreen
import com.example.kurrirapps.ui.screen.PesananMasuk
import com.example.kurrirapps.ui.screen.profileScreen
import com.example.kurrirapps.ui.screen.screenAkhir
import com.example.kurrirapps.viewModelFactory
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch


@Composable
fun Navigation(lifecycleOwner: LifecycleOwner){
    val navController = rememberNavController()
    val lifecycleScope = lifecycleOwner.lifecycleScope
    var uriImageHandler : Uri = Uri.EMPTY
    val context = LocalContext.current
    var whichImage by remember { mutableStateOf(EnumPhotoCapture.NOT_YET_SETTED) }

    var capturedImageOrderUri by remember { mutableStateOf<Uri>(Uri.EMPTY) }
    val googleAuthUiClient by lazy {
        GoogleAuthClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }

    NavHost(navController , startDestination = Screen.ScreenLogin.route ) {
        composable(Screen.ScreenLogin.route) {
            val viewModel = viewModel<SignInViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = Unit) {
                if (googleAuthUiClient.getSignedInUser() != null) {
                    navController.navigate(Screen.ViewHotelModel.route)
                }
            }
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == RESULT_OK) {
                        lifecycleScope.launch {
                            val signInResult = googleAuthUiClient.SignInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    Toast.makeText(context, "Sign in Success", Toast.LENGTH_LONG).show()

                    navController.navigate(Screen.ViewHotelModel.route)
                    viewModel.resetState()
                }
            }

            LoginScreen(
                state = state,
                onSignInClick = {
                    lifecycleScope.launch {
                        val signIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signIntentSender ?: return@launch
                            ).build()
                        )
                    }
                }
            )

        }
        composable(Screen.ViewHotelModel.route) {
            val hotelListScreenVM: HotelListScreenViewModel = viewModel(
                factory = viewModelFactory { HotelListScreenViewModel(MyApp.appModule.hotelRepository) }
            )
            val HotelListScreenUiState = hotelListScreenVM.state.collectAsStateWithLifecycle().value
            val userListScreenEffectFlow = hotelListScreenVM.effect


            val KurirVM: KurirViewModel = viewModel(
                factory = viewModelFactory { KurirViewModel(MyApp.appModule.kurirRepositoryImpl,
                    idKurir = googleAuthUiClient.getSignedInUser()!!.userId) }
            )
            val KurirUiState = KurirVM.state.collectAsStateWithLifecycle().value
            val kurirEffectFlow = KurirVM.effect

            ScreenViewHotelModel(
                userData = googleAuthUiClient.getSignedInUser()!!,
                kurirViewModel = KurirViewModel(
                    kurirRepositoryImpl = KurirRepositoryImpl(db = FirebaseFirestore.getInstance()),
                    idKurir = googleAuthUiClient.getSignedInUser()!!.userId
                ),
                onNavigateToScreen = {navController.navigate(it)}
//                hotelListScreenUiState = HotelListScreenUiState,
//                hotelListScreenEffectFlow = userListScreenEffectFlow,
//                onHotelEvent =hotelListScreenVM::onEvent
            )
        }
        composable(Screen.PesananMasuk.route) {

            val hotelListScreenVM: HotelListScreenViewModel = viewModel(
                factory = viewModelFactory { HotelListScreenViewModel(MyApp.appModule.hotelRepository) }
            )
            val HotelListScreenUiState = hotelListScreenVM.state.collectAsStateWithLifecycle().value
            val userListScreenEffectFlow = hotelListScreenVM.effect



            val pesananScreenVM: PesananViewModel = viewModel(
                factory = viewModelFactory { PesananViewModel(MyApp.appModule.pesananRepository) }
            )
            val pesananScreenVMUiState = pesananScreenVM.state.collectAsState().value
            val homeScreenVMEffectFlow = pesananScreenVM.effect
            Log.d("Screen", "uid : ${googleAuthUiClient.getSignedInUser()?.userId}")

            LaunchedEffect(key1 = googleAuthUiClient.getSignedInUser()?.userId != null) {
                Toast.makeText(context, "Sign in Success", Toast.LENGTH_LONG).show()
                val kurirData = googleAuthUiClient.getSignedInUser()

                Log.d("NAVIGATION : ", "logged in user firebase data : ${kurirData?.userId}")

                kurirData?.let { fetchedKurir ->
                    val existingKurir =
                        MyApp.appModule.kurirRepositoryImpl.getKurirById(fetchedKurir.userId)
                    Log.d(
                        "NAVIGATION : ",
                        "logged in user firebase data : $existingKurir"
                    )

                    if (existingKurir.name.isEmpty()) {
                        val newKurir = KurirModel(
                            name = fetchedKurir.username
                        )

                        try {
                            MyApp.appModule.kurirRepositoryImpl.addOrUpdateCustomer(
                                fetchedKurir.userId,
                                newKurir
                            )
                            Log.d("NAVIGATION : ", "registering user success")
                        } catch (e: Exception) {
                            Log.d("NAVIGATION : ", "failed registering user $e")
                        }
                    }
                }
            }

            PesananMasuk(
                userData = googleAuthUiClient.getSignedInUser(),
                onNavigateToScreen = { navController.navigate(it) },
                pesananListScreenUiState = pesananScreenVMUiState,
                onPesananMasukEvent = pesananScreenVM::onEvent,
                hotelListScreenUiState = HotelListScreenUiState,
                hotelListScreenEffectFlow = userListScreenEffectFlow,
            )

        }

//        composable(Screen.list){
//            listPesanan(onNavigateToScreen = { navController.navigate(it)})
//        }
        composable(Screen.KonfirmDgnFoto.route) {
//            val pesananViewModel: PesananViewModel = viewModel(
//                factory = viewModelFactory {
//                    PesananViewModel(
//                        MyApp.appModule.pesananRepositoryImpl
//                    )
//                }
//            )
//            val pesananScreenVMUiState = pesananViewModel.state.collectAsState().value
            KonfirmDgnFoto(
                userData = googleAuthUiClient.getSignedInUser()!!,
                onNavigateToPesananMasukScreen = {
                    navController.popBackStack()
                    navController.popBackStack()
                },
                pesananViewModel = PesananViewModel(
                    PesananRepository(db = FirebaseFirestore.getInstance())
                )
            )

        }

        composable(Screen.screenAkhir.route) {
            screenAkhir(
                userData = googleAuthUiClient.getSignedInUser(),
                onNavigateToScreen = { navController.navigate(it) },
                onNavigateToPesananMasukScreen = {
                    navController.popBackStack()
                    navController.popBackStack()
                }

            )

        }

        composable(Screen.ScreenProfile.route) {
            profileScreen(
                userData = googleAuthUiClient.getSignedInUser(),
                onSignOut = {
                    lifecycleScope.launch {
                        googleAuthUiClient.signOut()
                        Toast.makeText(context, "Signed Out", Toast.LENGTH_LONG).show()


                    }
                },
                onNavigateToScreen = { navController.navigate(it) }
            )
        }

    }
}



