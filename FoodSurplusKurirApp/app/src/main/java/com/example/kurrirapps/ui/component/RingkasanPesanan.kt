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
    var totalHargaPlusOngkir = 0F
    var ongkir = 0F
    var ongkirPrice : Float?
    var alamatTujuan = ""

    selectedKatalis.forEach { totalHarga += (it.hargaKatalis * it.quantity) }
    selectedKatalis.forEach { totalHargaPlusOngkir = totalHarga + it.onkir }
    selectedKatalis.forEach { ongkir = it.onkir }
    selectedKatalis.forEach { alamatTujuan = it.alamatTujuan }



    Column (
        modifier = Modifier
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(8.dp)
    ) {
        selectedKatalis.forEach {
            LeftRightText(it.namaKatalis + " x " +  it.quantity, it.hargaKatalis * it.quantity)
        }

        if (hotelToUserDistance != 0f) {
            ongkirPrice = hotelToUserDistance.div(10)

            LeftRightText("Biaya transportasi", totalHarga + ongkirPrice!!)
            Spacer(modifier = Modifier.height(16.dp))
            LeftRightText("Total Harga", totalHarga + ongkirPrice!!)
        }

        else {
            Spacer(modifier = Modifier.height(16.dp))
            LeftRightText("Total Harga", totalHarga)
        }
    }

    Spacer(modifier = Modifier.height(10.dp))

    Column(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Alamat Tujuan : $alamatTujuan")
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