package com.example.kurrirapps.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kurrirapps.ui.theme.KurrirAppsTheme

@Composable
fun SisipkanPesan() {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Sisipkan Pesan",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            shape = RoundedCornerShape(20.dp),
            label = { Text("Message") },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(20.dp)
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun sisipkanPesanPreview(){
    KurrirAppsTheme {
        SisipkanPesan()
    }
}