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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
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


@Composable
fun PilihAktivitasScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pilih Jenis Aktivitas",
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        color = mWhite
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                         navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "arrow_back",
                            tint = mWhite
                        )
                    }
                },
                backgroundColor = mRedMain
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
                caption = "Aktivitas yang dilakukan sehari-hari dengan intensitas rendah dan tidak memerlukan usaha tinggi",
                onClick = {
                    navController.navigate(AppScreen.DetailAktivitasScreen.screen_route)
                },
            )
            CustomPilihAktivitasCard(
                modifier = Modifier.padding(top = 16.dp),
                title = "Aktivitas Ringan",
                caption = "Aktivitas yang dilakukan sehari-hari dengan intensitas rendah dan tidak memerlukan usaha tinggi",
                onClick = {},
            )
            CustomPilihAktivitasCard(
                modifier = Modifier.padding(top = 16.dp),
                title = "Aktivitas Ringan",
                caption = "Aktivitas yang dilakukan sehari-hari dengan intensitas rendah dan tidak memerlukan usaha tinggi",
                onClick = {},
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
    onClick: () -> Unit
) {
    val rnd = Random()
    val red = rnd.nextInt(256)
    val green = rnd.nextInt(256)
    val blue = rnd.nextInt(256)
    val randomColor = Color(red, green, blue)

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
                    .background(color = randomColor)
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
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = caption,
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Normal,
                        color = mGrayScale
                    )
                    Text(
                        text = "Cal",
                        modifier = Modifier.padding(start = 4.dp),
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.SemiBold,
                        color = mGrayScale
                    )
                }
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