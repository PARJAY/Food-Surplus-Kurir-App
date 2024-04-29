package com.example.kurrirapps.ui.component

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kurrirapps.R
import com.example.kurrirapps.presentation.auth.UserData
import com.example.kurrirapps.ui.theme.KurrirAppsTheme

@Composable
fun Daftarkomponen() {

    Row(
        modifier = Modifier
            .height(65.dp)
            .width(319.dp)
            .border(
                BorderStroke(1.dp, Color.Black),shape = RoundedCornerShape(16.dp)
            )
            .padding(
                start = 15.dp,
                top = 5.dp,
                bottom = 5.dp,

            )

    ){
        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            )
        Spacer(modifier = Modifier.width(20.dp))
        Column (
            modifier = Modifier
                .padding(
                    top = 5.dp,

                )
        ){
            Text(text = "Hotel Gilang",
                fontWeight = FontWeight.Bold)
            Text(text = "Jalan Raya Jauh",
                fontSize = 12.sp)

        }
    }
}





@Preview(showBackground = true)
@Composable
fun Daftarkomponenpreview(){
    KurrirAppsTheme {
        Daftarkomponen()
    }
}