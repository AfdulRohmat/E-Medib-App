package com.example.e_medib.features.aktivitas_feature.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_medib.ui.theme.mBlack
import com.example.e_medib.ui.theme.mLightGrayScale
import com.example.e_medib.ui.theme.mRedMain
import com.example.e_medib.ui.theme.mWhite

@Composable
fun DetailAktivitasScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Aktivitas Ringan",
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
            // COLUMN LIST AKTIVITAS
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                // LIST AKTITVITAS
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    LazyColumn() {
                        items(10) {
                            CustomListAktivitasCard()
                        }
                    }

                }
            }

            // BUTTON
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            ) {
                Button(
                    onClick = {
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = mRedMain,
                        contentColor = mWhite,
                        disabledBackgroundColor = mLightGrayScale,
                        disabledContentColor = mBlack
                    ),
                    shape = RoundedCornerShape(32.dp),

                    ) {
                    Text(
                        text = "Tambahkan ke Diary",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.SemiBold,
                        color = mWhite
                    )
                }
            }

        }
    }
}

@Composable
fun CustomListAktivitasCard(modifier: Modifier = Modifier) {
    val checked = remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(
            modifier = modifier.height(50.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked.value,
                onCheckedChange = {
                    checked.value = it
                },
                modifier = modifier.weight(1f),
                colors = CheckboxDefaults.colors(
                    checkedColor = mRedMain,
                    uncheckedColor = mRedMain
                )
            )

            Column(
                modifier = modifier.weight(4f),
            ) {
                Text(
                    text = "Pilates",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.SemiBold,
                    color = mBlack
                )
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "45 Menit",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Normal,
                        color = mRedMain
                    )
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            modifier = modifier.size(18.dp),
                            contentDescription = "edit",
                            tint = mRedMain
                        )
                    }
                }

            }
            Text(
                text = "250 Cal",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                color = mRedMain
            )
        }
        Divider(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            color = mLightGrayScale,
            thickness = 2.dp
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun DetailAktivitasScreenPreview() {
    val navController = rememberNavController()

    DetailAktivitasScreen(navController)

}

