package com.example.kurrirapps.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kurrirapps.ui.theme.KurrirAppsTheme

@Composable
fun Konfirmasi(onNavigateToPesananMasukScreen:()->Unit){
    Button(
        modifier = Modifier
            .height(50.dp)
            .width(180.dp),
        onClick = { onNavigateToPesananMasukScreen()},
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
@Preview(showBackground = true)
@Composable
fun Konfirmasipreview(){
    KurrirAppsTheme {
        Konfirmasi(
            onNavigateToPesananMasukScreen = {}
        )
    }
}