package com.example.kurrirapps.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kurrirapps.ui.navigation.Screen
import com.example.kurrirapps.ui.theme.KurrirAppsTheme

@Composable
fun listPesanan(
    onNavigateToScreen:(String)->Unit
){
    Column(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(8.dp),

    ) {
        Text(text = "Adiodo",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(text = "Nasi Goreng")
        Text(text = "Jalan Ayani")
        Text(text = "08123456788")
        Text(text = "2km")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = {onNavigateToScreen(Screen.KonfirmDgnFoto.route)},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green
                ),
            ) {
                Text(text = "Ambil",
                    style = TextStyle(
                        fontSize = (18.sp),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black

                    ))

            }
        }
    }

}
@Preview(showBackground = true)
@Composable
fun KonfirmasiPreview(){
    KurrirAppsTheme {
        listPesanan(onNavigateToScreen = {})
    }
}


















