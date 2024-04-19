package com.example.kurrirapps.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kurrirapps.ui.theme.KurrirAppsTheme


@Composable
fun ButtonKonfirmasi(navController: NavController){
    Column {
        Button(onClick = {navController.navigate("pesananMasuk")}) {
           Text(text = "Konfirmasi")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun konfirmasiPreview() {
    KurrirAppsTheme {
//        ButtonKonfirmasi()
    }
}