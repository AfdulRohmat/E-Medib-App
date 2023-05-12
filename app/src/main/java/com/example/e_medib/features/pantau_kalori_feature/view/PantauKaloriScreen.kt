package com.example.e_medib.features.pantau_kalori_feature.view

import CustomExpandedCard
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_medib.R
import com.example.e_medib.features.home_feature.view.HomeScreenComponent
import com.example.e_medib.navigations.EMedibAppScreen
import com.example.e_medib.ui.theme.*
import com.foreverrafs.datepicker.DatePickerTimeline
import com.foreverrafs.datepicker.state.rememberDatePickerState
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun PantauKaloriScreen(navController: NavController) {
    val datePickerState =
        rememberDatePickerState(initialDate = LocalDate.now())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Pantau Konsumsi Kalori Harianmu",
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
        // CARD KONSUMSI
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
                // WAKTU SARAPAN
                CustomExpandedCard(
                    header = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(

                                painter = painterResource(id = R.drawable.icon_pagi),
                                contentDescription = "app_logo",
                                modifier = Modifier
                                    .size(24.dp)
                                    .weight(1f)
                            )
                            Column(
                                modifier = Modifier
                                    .weight(6f)
                                    .padding(start = 4.dp),
                            ) {
                                Text(
                                    text = "Sarapan",
                                    style = MaterialTheme.typography.body1,
                                    fontWeight = FontWeight.Bold,
                                    color = mGrayScale
                                )
                                Row(
                                    modifier = Modifier.padding(top = 4.dp),
                                    verticalAlignment = Alignment.Bottom
                                ) {
                                    Text(
                                        text = "1234",
                                        style = MaterialTheme.typography.caption,
                                        fontWeight = FontWeight.SemiBold,
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
                            IconButton(modifier = Modifier.weight(1f), onClick = {
                                navController.navigate(EMedibAppScreen.SearchMenuScreen.name)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.AddCircle,
                                    contentDescription = "Add",
                                    tint = mRedMain,
                                )
                            }


                        }
                    },
                    body = {
                        Column() {
                            RowItem(
                                title = "Bubur Ayam",
                                portion = "1 porsi (150 g)",
                                calories = "165 Cal",
                                onClick = {}
                            )
                            RowItem(
                                title = "Bubur Ayam",
                                portion = "1 porsi (150 g)",
                                calories = "165 Cal",
                                onClick = {}
                            )
                            RowItem(
                                title = "Bubur Ayam",
                                portion = "1 porsi (150 g)",
                                calories = "165 Cal",
                                onClick = {}
                            )
                        }
                    }
                )

                // MAKAN SIANG
                CustomExpandedCard(
                    modifier = Modifier.padding(top = 16.dp),
                    header = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_siang),
                                contentDescription = "app_logo",
                                modifier = Modifier
                                    .size(24.dp)
                                    .weight(1f)
                            )
                            Column(
                                modifier = Modifier
                                    .weight(6f)
                                    .padding(start = 4.dp),
                            ) {
                                Text(
                                    text = "Makan Siang",
                                    style = MaterialTheme.typography.body1,
                                    fontWeight = FontWeight.Bold,
                                    color = mGrayScale
                                )
                                Row(
                                    modifier = Modifier.padding(top = 4.dp),
                                    verticalAlignment = Alignment.Bottom
                                ) {
                                    Text(
                                        text = "1234",
                                        style = MaterialTheme.typography.caption,
                                        fontWeight = FontWeight.SemiBold,
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
                            IconButton(modifier = Modifier.weight(1f), onClick = { }) {
                                Icon(
                                    imageVector = Icons.Default.AddCircle,
                                    contentDescription = "Add",
                                    tint = mRedMain,
                                )
                            }


                        }
                    },
                    body = {},
                    isExpanded = false
                )

                // MAKAN MALAM
                CustomExpandedCard(
                    modifier = Modifier.padding(top = 16.dp),
                    header = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(

                                painter = painterResource(id = R.drawable.icon_malam),
                                contentDescription = "app_logo",
                                modifier = Modifier
                                    .size(24.dp)
                                    .weight(1f)
                            )
                            Column(
                                modifier = Modifier
                                    .weight(6f)
                                    .padding(start = 4.dp),
                            ) {
                                Text(
                                    text = "Makan Malam",
                                    style = MaterialTheme.typography.body1,
                                    fontWeight = FontWeight.Bold,
                                    color = mGrayScale
                                )
                                Row(
                                    modifier = Modifier.padding(top = 4.dp),
                                    verticalAlignment = Alignment.Bottom
                                ) {
                                    Text(
                                        text = "1234",
                                        style = MaterialTheme.typography.caption,
                                        fontWeight = FontWeight.SemiBold,
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
                            IconButton(modifier = Modifier.weight(1f), onClick = { }) {
                                Icon(
                                    imageVector = Icons.Default.AddCircle,
                                    contentDescription = "Add",
                                    tint = mRedMain,
                                )
                            }


                        }
                    },
                    body = {},
                    isExpanded = false
                )


                // CAMILAN / LAINNYA
                CustomExpandedCard(
                    modifier = Modifier.padding(top = 16.dp),
                    header = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(

                                painter = painterResource(id = R.drawable.icon_cemilan),
                                contentDescription = "app_logo",
                                modifier = Modifier
                                    .size(24.dp)
                                    .weight(1f)
                            )
                            Column(
                                modifier = Modifier
                                    .weight(6f)
                                    .padding(start = 4.dp),
                            ) {
                                Text(
                                    text = "Camilan / Lainnya",
                                    style = MaterialTheme.typography.body1,
                                    fontWeight = FontWeight.Bold,
                                    color = mGrayScale
                                )
                                Row(
                                    modifier = Modifier.padding(top = 4.dp),
                                    verticalAlignment = Alignment.Bottom
                                ) {
                                    Text(
                                        text = "1234",
                                        style = MaterialTheme.typography.caption,
                                        fontWeight = FontWeight.SemiBold,
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
                            IconButton(modifier = Modifier.weight(1f), onClick = {
                            }) {
                                Icon(
                                    imageVector = Icons.Default.AddCircle,
                                    contentDescription = "Add",
                                    tint = mRedMain,
                                )
                            }


                        }
                    },
                    body = {},
                    isExpanded = false
                )


                // TOTAL KALORI
                CustomExpandedCard(
                    modifier = Modifier.padding(top = 16.dp),
                    header = {
                        Column() {
                            Text(
                                text = "Total Kalori",
                                style = MaterialTheme.typography.body1,
                                fontWeight = FontWeight.Bold,
                                color = mBlack
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // PROGRESS
                                LinearProgressIndicator(
                                    modifier = Modifier
                                        .weight(2f)
                                        .clip(RoundedCornerShape(10.dp)),
                                    color = mRedMain,
                                    backgroundColor = mLightGrayScale,
                                    progress = 0.7f
                                )


                                // STATUS
                                Row(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "165",
                                        style = MaterialTheme.typography.body1,
                                        fontWeight = FontWeight.SemiBold,
                                        color = mRedMain
                                    )
                                    Text(
                                        text = "/ 1865 Cal",
                                        style = MaterialTheme.typography.body1,
                                        fontWeight = FontWeight.SemiBold,
                                        color = mGrayScale
                                    )
                                }

                            }
                        }
                    },
                    body = {
                        Column() {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Karbohidrat",
                                    style = MaterialTheme.typography.caption,
                                    fontWeight = FontWeight.SemiBold,
                                    color = mGrayScale
                                )
                                Text(
                                    text = "57 %",
                                    style = MaterialTheme.typography.caption,
                                    fontWeight = FontWeight.Normal,
                                    color = mGrayScale
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Karbohidrat",
                                    style = MaterialTheme.typography.caption,
                                    fontWeight = FontWeight.SemiBold,
                                    color = mGrayScale
                                )
                                Text(
                                    text = "57 %",
                                    style = MaterialTheme.typography.caption,
                                    fontWeight = FontWeight.Normal,
                                    color = mGrayScale
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Karbohidrat",
                                    style = MaterialTheme.typography.caption,
                                    fontWeight = FontWeight.SemiBold,
                                    color = mGrayScale
                                )
                                Text(
                                    text = "57 %",
                                    style = MaterialTheme.typography.caption,
                                    fontWeight = FontWeight.Normal,
                                    color = mGrayScale
                                )
                            }
                        }

                    },
                )
            }


        }
    }
}


@Composable
fun RowItem(
    title: String,
    portion: String,
    calories: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.SemiBold,
            color = mGrayScale
        )
        Text(
            text = portion,
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.Normal,
            color = mGrayScale
        )
        Text(
            text = calories,
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.SemiBold,
            color = mGrayScale
        )
        Icon(
            imageVector = Icons.Default.NavigateNext,
            contentDescription = "NavigateNext",
            tint = mRedMain,
        )


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun PantauKaloriScreenPreview() {
// PantauKaloriScreen()
}