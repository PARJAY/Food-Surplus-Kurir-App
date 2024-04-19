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
            .background(Brown)
            .padding(16.dp),
        contentAlignment = Alignment.Center,
        )
    {
        Box(
            modifier = Modifier
                .border(5.dp, color = Color.Gray,
//                    shape = RoundedCornerShape(16.dp)
                )
                .background(yellow)
                .height(580.dp)
                .width(320.dp)
                ){
            Column (
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Spacer(modifier = Modifier.height(45.dp))
                Text(text = "Login",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = (50.sp),
                        color = (Color.Black),
                    )
                )
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "Selamat Datang Silahkan Login Dengan Akun User Anda",
                    style = TextStyle(
                        fontSize = (26.sp),
                        color = (Color.Black),
                        textAlign = TextAlign.Center
                    )
                )
                Spacer(modifier = Modifier.height(200.dp))
                Button(
                    modifier = Modifier
                        .width(250.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    onClick = { onSignInClick() }
                ) {
                    Image(painter = painterResource(id = com.example.kurrirapps.R.drawable.devicon_google), contentDescription ="Google Icon" )
                    Spacer(modifier = Modifier.width(23.dp))
                    Text(
                        text = "Sign in With Google",
                        style = TextStyle(
                            fontSize = (20.sp)
                        ))
                }
            }
        }
    }
}



//Button(onClick = { onSignInClick() }) {
////                    Text(text = "Sign In")