package com.example.e_medib.features.profile_feature.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.e_medib.ui.theme.mBlack
import com.example.e_medib.ui.theme.mGrayScale
import com.example.e_medib.ui.theme.mRedMain

@Composable
fun BMRScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        CustomProfileCard(
            header = {
                Text(
                    text = "BMR Anda",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = mBlack
                )
            },
            body = {
                Text(
                    text = "1564 Cal/hari",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = mRedMain
                )
            },
        )
        Spacer(modifier = Modifier.height(14.dp))
        CustomProfileCard(
            header = {
                Text(
                    text = "Keterangan",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = mBlack
                )
            },
            body = {
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "Anda membutuhkan setidaknya 1636 kalori perhari.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start,
                        color = mBlack
                    )
                    Text(
                        text = "Jumlah kalori tersebut merupakan jumlah kalori yang dibutuhkan tubuh untuk melakukan aktivitas dasar tubuh seperti bernapas, memompa jantung, mencerna makanan, memperbaiki sel tubuh, dan lain sebagainya.",
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Justify,
                        color = mBlack
                    )
                }
            },
        )

    }
}