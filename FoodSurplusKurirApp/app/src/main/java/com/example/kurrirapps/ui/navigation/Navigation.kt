package com.example.kurrirapps.ui.navigation

import android.app.Activity.RESULT_OK
import android.net.Uri
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kurrirapps.presentation.auth.SignInViewModel
import com.example.kurrirapps.ui.component.listPesanan
import com.example.kurrirapps.ui.screen.ButtonKonfirmasi
import com.example.kurrirapps.ui.screen.KonfirmDgnFoto
import com.example.kurrirapps.ui.screen.PesananMasuk
import com.example.kurrirapps.ui.screen.screenAkhir
import androidx.lifecycle.lifecycleScope
import com.example.kurrirapps.feature.EnumPhotoCapture
import com.example.kurrirapps.presentation.auth.GoogleAuthClient
import com.example.kurrirapps.ui.screen.LoginScreen
import com.example.kurrirapps.ui.screen.profileScreen
import com.google.android.gms.auth.api.identity.Identity
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

    NavHost(navController , startDestination = Screen.ScreenLogin.route ){
        composable(Screen.ScreenLogin.route){
            val viewModel = viewModel<SignInViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = Unit){
                if (googleAuthUiClient.getSignedInUser() !=null){
                    navController.navigate(Screen.PesananMasuk.route)
                }
            }
            val launcher = rememberLauncherForActivityResult(
                contract =  ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result->
                    if (result.resultCode == RESULT_OK){
                        lifecycleScope.launch {
                            val signInResult = googleAuthUiClient.SignInWithIntent(
                                intent = result.data?: return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful){
                if (state.isSignInSuccessful){
                    Toast.makeText(context, "Sign in Success", Toast.LENGTH_LONG).show()

                    navController.navigate(Screen.PesananMasuk.route)
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
                                signIntentSender?: return@launch
                            ).build()
                        )
                    }
                }
            )

        }
        composable(Screen.PesananMasuk.route){
            PesananMasuk(
                userData = googleAuthUiClient.getSignedInUser(),
                onNavigateToScreen = { navController.navigate(it)}
            )

        }

//        composable(Screen.list){
//            listPesanan(onNavigateToScreen = { navController.navigate(it)})
//        }
        composable(Screen.KonfirmDenganfoto.route){
            KonfirmDgnFoto(
                uriImageHandler = uriImageHandler,
                onImageCaptured = { isPhotoTakem, uriPhotoTaken ->
                    if (isPhotoTakem) when (whichImage) {
                        EnumPhotoCapture.OUTLET -> capturedImageOrderUri = uriImageHandler
                        EnumPhotoCapture.ODOMETER -> capturedImageOrderUri = uriImageHandler
                        else -> Toast.makeText(context, "Invalid", Toast.LENGTH_SHORT).show()
                    }
                },
                userData = googleAuthUiClient.getSignedInUser(),
                onNavigateToScreen = {navController.navigate(it)}
            )
        }

        composable(Screen.screenAkhir.route){
            screenAkhir(
                userData = googleAuthUiClient.getSignedInUser(),
                onNavigateToScreen = {navController.navigate(it)},
                onNavigateToPesananMasukScreen = {
                    navController.popBackStack()
                    navController.popBackStack()
                }
            )

        }

//        composable("TakePictureScreen") {
//            TakePicture(
//                uriImageHandler = uriImageHandler,
//                onImageCaptured = { isPhotoTakem, uriPhotoTaken ->
//                    capturedImageOrderUri = uriImageHandler
//                    navController.popBackStack()
//                }
//            )

//        }
        composable(Screen.ScreenProfile.route){
            profileScreen(
                userData = googleAuthUiClient.getSignedInUser(),
                onSignOut = {
                    lifecycleScope.launch {
                        googleAuthUiClient.signOut()
                            Toast.makeText(context, "Signed Out", Toast.LENGTH_LONG).show()


                    }
                },
                onNavigateToScreen = {navController.navigate(it)}
            )
        }

    }
}




