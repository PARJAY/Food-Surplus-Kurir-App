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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kurrirapps.data.model.PesananModel
import com.example.kurrirapps.presentation.pesanan.PesananEvent
import com.example.kurrirapps.ui.navigation.Screen

@Composable
fun listPesanan(
    onNavigateToScreen:(String)->Unit,
    pesananModel: PesananModel,
    onPesananMasukEvent: (PesananEvent) -> Unit
){
    Column(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(8.dp),

    ) {
        Text(text = pesananModel.id_user,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(text = pesananModel.id_pesanan)
        Text(text = pesananModel.id_hotel)
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
//@Preview(showBackground = true)
//@Composable
//fun KonfirmasiPreview(){
//    KurrirAppsTheme {
//        listPesanan(onNavigateToScreen = {},
//            pesananModel = PesananModel("ASDASDAFAFdas"))
//    }
//}


















