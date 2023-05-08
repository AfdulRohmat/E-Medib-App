package com.example.e_medib.features.auth_feature.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RegisterScreen(navController: NavController) {
    Scaffold() {
        Text(text = "RegisterScreen")
    }
}

@Composable
fun RegisterScreenComponent() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        // TITLE

        // NAMA LENGKAP

        // USERNAME

        // NIK

        // EMAIL

        // TANGGAL LAHIR

        // JENIS KELAMIN

        // TINGGI BADAN && BERAT BADAN

        // JENIS ALERGI

        // RIWAYAT PENYAKIT

        // PASSWORD

        // BUTTON

        // BALIK KE LOGIN

    }
}