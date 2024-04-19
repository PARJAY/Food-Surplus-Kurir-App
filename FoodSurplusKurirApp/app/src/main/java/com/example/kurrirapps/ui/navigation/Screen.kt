package com.example.kurrirapps.ui.navigation

sealed class Screen(val route : String) {
    data object ScreenLogin : Screen ("ScreenLogin")
    data object ScreenProfile : Screen ("ScreenProfile")
    data object PesananMasuk : Screen ("PesananMasuk")
    data object KonfirmDenganfoto : Screen ("KonfirmDenganfoto")
    data object screenAkhir : Screen ("screenAkhir")
}