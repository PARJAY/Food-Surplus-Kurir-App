package com.example.kurrirapps.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kurrirapps.presentation.auth.SignInState
import com.example.kurrirapps.ui.theme.Brown
import com.example.kurrirapps.ui.theme.HijauMuda
import com.example.kurrirapps.ui.theme.Krem
import com.example.kurrirapps.ui.theme.yellow

@Composable
fun LoginScreen(
    state: SignInState,
    onSignInClick: ()-> Unit
) {

    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInErrorMessage){
        state.signInErrorMessage?.let{ error->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(HijauMuda),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier
                .height(525.dp)
                .width(300.dp)
                .background(HijauMuda),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Krem,
                contentColor = Color.Black
            ),
            onClick = { /*TODO*/ }
        ) {
            Column (horizontalAlignment = Alignment.CenterHorizontally,){
                Spacer(modifier = Modifier.height(0.dp))
                Text(text = "Login",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "Selamat Datang di Kurir App, Silahkan Login dengan Akun Google Anda",
                    style = TextStyle(textAlign = TextAlign.Center)
                )
                Spacer(modifier = Modifier.height(200.dp))
                Button(
                    modifier = Modifier
                        .width(230.dp)
                        .height(70.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    onClick = { onSignInClick() }
                ) {
                    Image(painter = painterResource(id = com.example.kurrirapps.R.drawable.devicon_google), contentDescription ="Google Icon" )
                    Spacer(modifier = Modifier.width(25.dp))
                    Text(text = "Sign in With Google")
                }
            }
        }
    }
}



//Button(onClick = { onSignInClick() }) {
////                    Text(text = "Sign In")