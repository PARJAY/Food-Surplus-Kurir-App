package com.example.kurrirapps.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kurrirapps.presentation.pesanan.SelectedPesanan
import com.example.kurrirapps.ui.theme.HijauMuda
import com.example.kurrirapps.ui.theme.White

@Composable
fun RingkasanPesanan(
    selectedKatalis: SnapshotStateList<SelectedPesanan>,
    hotelToUserDistance : Float
) {

    var totalHarga = 0F
    var totalHargaPlusOngkir = 0F
    var ongkir = 0F
    var ongkirPrice : Float?
    var alamatTujuan = ""

    selectedKatalis.forEach { totalHarga += (it.hargaKatalis * it.quantity) }
    selectedKatalis.forEach { totalHargaPlusOngkir = totalHarga + it.onkir }
    selectedKatalis.forEach { ongkir = it.onkir }
    selectedKatalis.forEach { alamatTujuan = it.alamatTujuan }

    Button(
        modifier = Modifier.padding(8.dp),
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = HijauMuda,
            contentColor = Color.White
        )
    ) {
        Column {
            selectedKatalis.forEach {
                LeftRightText(it.namaKatalis + " x " +  it.quantity, it.hargaKatalis * it.quantity)
            }

            if (hotelToUserDistance != 0f) {
                ongkirPrice = hotelToUserDistance.div(10)

                LeftRightText("Biaya transportasi", totalHarga + ongkirPrice!!)
                Spacer(modifier = Modifier.height(16.dp))
                Box (modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(White)
                )
                LeftRightText("Total Harga", totalHarga + ongkirPrice!!)
            }

            else {
                Spacer(modifier = Modifier.height(16.dp))
                Box (modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(White)
                )
                LeftRightText("Total Harga", totalHarga)
            }
        }
    }
    Button(
        modifier = Modifier.padding(8.dp),
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = HijauMuda,
            contentColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Alamat Tujuan : $alamatTujuan")
        }
    }
}


// TODO : Untested
@Composable
fun LeftRightText(leftTextInfo : String, rightTextPrice : Float) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = leftTextInfo)
        Text(text = "Rp. $rightTextPrice")
    }
}