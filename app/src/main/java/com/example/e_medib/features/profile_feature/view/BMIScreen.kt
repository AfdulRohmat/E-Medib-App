package com.example.e_medib.features.profile_feature.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.e_medib.ui.theme.*

@Composable
fun BMIScreen() {
    val keterangan = rememberSaveable() {
        mutableStateOf(
            "Ini merupakan ‘Berat Badan Sehat’ karena ini adalah berat badan ideal untuk mendapatkan manfaat kesehatan terbaik." + "Anda akan terhindar dari kelelahan dan masalah sistem kekebalan tubuh yang meningkat karena kekurangan berat badan, dan juga kehilangan peningkatan risiko diabetes dan penyakit jantung yang ditimbulkan oleh kelebihan berat badan." + "Orang yang menangani masalah kesehatan dengan serius harus menargetkan BMI mereka dalam kisaran ini. Latihan diet yang masuk akal dan sedang akan membuat seseorang tetap dalam kategori ini."
        )
    }
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
                    text = "BMI Anda",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = mBlack
                )
            },
            body = {
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "22,1 kg/m2",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = mGreen
                    )
                    Text(
                        text = "Kamu memiliki BMI Normal",
                        modifier = Modifier
                            .clip(RoundedCornerShape(32.dp))
                            .fillMaxWidth()
                            .background(color = mGreen)
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = mWhite
                    )

                }
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
                        text = keterangan.value,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
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