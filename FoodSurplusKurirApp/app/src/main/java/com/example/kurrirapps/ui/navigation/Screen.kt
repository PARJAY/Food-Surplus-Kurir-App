package com.example.kurrirapps.ui.navigation

sealed class Screen(val route : String) {
    data object ScreenLogin : Screen ("ScreenLogin")
    data object ScreenProfile : Screen ("ScreenProfile")
    data object PesananMasuk : Screen ("PesananMasuk")
    data object KonfirmDgnFoto : Screen ("KonfirmDgnFoto")
    data object screenAkhir : Screen ("screenAkhir")
    data object ViewHotelModel : Screen ("ViewHotelModel")
    data object ScreenRingkasanPesanan : Screen ("ScreenRingkasanPesanan")
}