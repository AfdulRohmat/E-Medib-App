package com.example.e_medib.navigations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DirectionsBike
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ReceiptLong
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppScreen(var title: String, var icon: ImageVector?, var screen_route: String) {
    // BOTTOM NAVIGATION
    object Beranda : AppScreen("Beranda", Icons.Rounded.Home, "Beranda")
    object PantauKalori : AppScreen("Pantau Kalori", Icons.Rounded.ReceiptLong, "PantauKalori")
    object PilihAktivitasScreen :
        AppScreen("Pilih Aktivitas", Icons.Rounded.DirectionsBike, "PilihAktivitasScreen")

    object Profil : AppScreen("Profil", Icons.Rounded.Person, "Profil")

    // NORMAL SCREEN
    object SplashScreen : AppScreen("SplashScreen", null, "SplashScreen")
    object LoginScreen : AppScreen("LoginScreen", null, "LoginScreen")
    object RegisterScreen : AppScreen("RegisterScreen", null, "RegisterScreen")
    object SearchMenuScreen : AppScreen("SearchMenuScreen", null, "SearchMenuScreen")
    object HitungBMIScreen : AppScreen("HitungBMIScreen", null, "HitungBMIScreen")
    object DowwnloadRekapScreen : AppScreen("DowwnloadRekapScreen", null, "DowwnloadRekapScreen")


    object Aktivitas : AppScreen("Aktivitas", null, "Aktivitas")
    object MainScreen : AppScreen("MainScreen", null, "MainScreen")

    object DetailAktivitasScreen : AppScreen("DetailAktivitasScreen", null, "DetailAktivitasScreen")

}
