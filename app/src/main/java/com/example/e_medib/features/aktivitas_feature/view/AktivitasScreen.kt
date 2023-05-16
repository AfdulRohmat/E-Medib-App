package com.example.e_medib.features.aktivitas_feature.view

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_medib.navigations.AppScreen
import com.example.e_medib.ui.theme.*
import com.foreverrafs.datepicker.DatePickerTimeline
import com.foreverrafs.datepicker.state.rememberDatePickerState
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AktivitasScreen(navController: NavController) {
    val datePickerState =
        rememberDatePickerState(initialDate = LocalDate.now())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Rekomendasi Aktivitas Hari ini",
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
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // DATE ROW
            DatePickerTimeline(
                modifier = Modifier,
                state = datePickerState,
                selectedBackgroundColor = mRedMain,
                selectedTextColor = mWhite,
                dateTextColor = mGrayScale,
                pastDaysCount = 7,
                onDateSelected = { selectedDate: LocalDate ->
                    //do something with selected date
                    Log.d("date", "PantauKaloriScreen: $selectedDate")
                },
            )

            // COLUMN LAYOUT
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
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
                                CustomAktivitasCard()
                            }
                        }

                    }
                }

                // ROW BUTTON
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Button Pilih Aktivitas Lain
                    OutlinedButton(
                        onClick = {
                            navController.navigate(AppScreen.PilihAktivitasScreen.screen_route)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = mWhite,
                            contentColor = mRedMain,
                            disabledBackgroundColor = mLightGrayScale,
                            disabledContentColor = mBlack
                        ),
                        border = BorderStroke(2.dp, mRedMain),
                        shape = RoundedCornerShape(32.dp),

                        ) {
                        Text(
                            text = "Pilih Aktivitas Lain",
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.SemiBold,
                            color = mRedMain
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Button Tambah ke Diary
                    Button(
                        onClick = {
                        },
                        modifier = Modifier
                            .weight(1f)
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
                            text = "Tambah ke Diary",
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.SemiBold,
                            color = mWhite
                        )
                    }

                }

            }


        }


    }
}

@Composable
fun CustomAktivitasCard(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(
            modifier = modifier.height(50.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
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
fun AktivitasScreenPreview() {
//    AktivitasScreen()
}