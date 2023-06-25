package com.example.e_medib.features.aktivitas_feature.view


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_medib.navigations.AppScreen
import com.example.e_medib.ui.theme.*
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PilihKategoryAktivitasScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Pilih Intensitas Aktivitas",
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        color = mWhite
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = mRedMain)
            )

        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            CustomPilihAktivitasCard(
                modifier = Modifier,
                title = "Aktivitas Ringan",
                caption = "Aktivitas fisik ini hanya memerlukan sedikit tenaga dan biasanya tidak menyebabkan perubahan dalam pernapasan, saat melakukan aktivitas masih dapat berbicara dan bernyanyi",
                color = mGreen,
                onClick = {
                    navController.navigate(AppScreen.AktivitasPenggunaScreen.screen_route + "/RINGAN")
                },
            )
            CustomPilihAktivitasCard(
                modifier = Modifier.padding(top = 16.dp),
                title = "Aktivitas Sedang",
                caption = "Aktivitas fisik sedang adalah aktivitas fisik dengan kebutuhan kalori sebesar 3,5 - 7 Kcal/menit. Saat menjalankan aktivitas fisik sedang, tubuh akan sedikit berkeringat, ritme napas lebih cepat, dan denyut jantung sedikit lebih kuat",
                color = mYellow,
                onClick = {
                    navController.navigate(AppScreen.AktivitasPenggunaScreen.screen_route + "/SEDANG")
                },
            )
            CustomPilihAktivitasCard(
                modifier = Modifier.padding(top = 16.dp),
                title = "Aktivitas Berat",
                caption = "Aktivitas fisik dikategorikan berat apabila selama beraktivitas tubuh mengeluarkan banyak berkeringat, denyut jantung dan frekuensi nafas sangat meningkat sampai dengan kehabisan napas.Energi yang dikeluarkan saat melakukan aktivitas pada kategori ini > 7 Kcal/menit.",
                color = mRedMain,
                onClick = {
                    navController.navigate(AppScreen.AktivitasPenggunaScreen.screen_route + "/BERAT")
                },
            )


        }

    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomPilihAktivitasCard(
    modifier: Modifier = Modifier,
    title: String,
    caption: String,
    color: Color,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, color = mLightGrayScale),
        elevation = 0.dp,
        onClick = { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(100.dp))
                    .size(24.dp)
                    .background(color)
            )
            Column(
                modifier = Modifier
                    .weight(6f)
                    .padding(start = 16.dp),
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    color = mBlack
                )
                Text(
                    text = caption,
                    modifier = Modifier.padding(top = 4.dp),
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Normal,
                    color = mGrayScale
                )

            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "ChevronRight",
                tint = mRedMain,
            )


        }

    }


}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun PilihAktivitasScreenPreview() {
//    PilihAktivitasScreen()
}