package com.example.kurrirapps.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kurrirapps.presentation.pesanan.SelectedPesanan

@Composable
fun RingkasanPesanan(
    selectedKatalis: SnapshotStateList<SelectedPesanan>,
    hotelToUserDistance : Float
) {

    var totalHarga = 0F

    selectedKatalis.forEach { totalHarga += (it.hargaKatalis * it.quantity) }
    var ongkirPrice : Float?
    var bensinPrice : Float?


    Column(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(8.dp)
    ) {


            Column {
                selectedKatalis.forEach {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = it.namaKatalis + " x " +  it.quantity )
                        Text(text = "Rp." + (it.hargaKatalis* it.quantity ))
                    }
                }
            }

            Column {
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
