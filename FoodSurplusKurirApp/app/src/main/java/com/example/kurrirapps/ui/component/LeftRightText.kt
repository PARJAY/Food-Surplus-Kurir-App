package com.example.kurrirapps.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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