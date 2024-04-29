package com.example.kurrirapps.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kurrirapps.data.model.PesananModel
import com.example.kurrirapps.presentation.pesanan.SelectedKatalis

@Composable
fun RingkasanPesanan(
    selectedKatalis: SnapshotStateList<SelectedKatalis>,
    hotelToUserDistance : Float,
    ) {

    var totalHarga = 0F

    selectedKatalis.forEach { totalHarga += (it.hargaKatalis * it.quantity) }
    var ongkirPrice : Float?
    var bensinPrice : Float?

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "Ringkasan Pesanan",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

            )
            Spacer(modifier = Modifier.size(width = 0.dp, height = 8.dp))
        }


        items(selectedKatalis) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = it.namaKatalis + " x " +  it.quantity )
                Text(text = "Rp." + (it.hargaKatalis* it.quantity ))
            }
        }

        item {
            if (hotelToUserDistance != 0f) {
                ongkirPrice = hotelToUserDistance.div(10)
                bensinPrice = hotelToUserDistance.times(1.5f)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Biaya transportasi")
                    Text(text = "Rp. ${ongkirPrice!! + bensinPrice!!}")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Total Harga")
                    Text(text = "Rp. ${totalHarga + ongkirPrice!! + bensinPrice!!}")
                }
            }
            else {
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Total Harga")
                    Text(text = "Rp. $totalHarga")
                }
            }

        }
    }
}