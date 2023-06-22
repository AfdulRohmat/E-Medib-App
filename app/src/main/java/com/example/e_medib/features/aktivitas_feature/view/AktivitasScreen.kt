package com.example.e_medib.features.aktivitas_feature.view

import CustomBottomSheet
import CustomInputField
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.e_medib.features.aktivitas_feature.model.DataUbahDurasiAktivitasModel
import com.example.e_medib.features.aktivitas_feature.view_model.AktivitasViewModel
import com.example.e_medib.ui.theme.*
import com.example.e_medib.utils.CustomDataStore
import com.foreverrafs.datepicker.DatePickerTimeline
import com.foreverrafs.datepicker.state.rememberDatePickerState
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AktivitasScreen(
    navController: NavController,
    aktivitasViewModel: AktivitasViewModel = hiltViewModel(),
    tingkat_aktivitas: String?
) {
    val datePickerState =
        rememberDatePickerState(initialDate = LocalDate.now())

    val context = LocalContext.current
    val store = CustomDataStore(context)
    val tokenText = store.getAccessToken.collectAsState(initial = "")

    LaunchedEffect(Unit, block = {
        val headerMap = mutableMapOf<String, String>()
        headerMap["Accept"] = "application/json"
        headerMap["Authorization"] = "Bearer ${tokenText.value}"
        if (tingkat_aktivitas != null) {
            aktivitasViewModel.getAllAktivitas(headerMap, tingkat_aktivitas)
        }
        Log.d("tingkat_aktivitas", "$tingkat_aktivitas")
    })

    if (aktivitasViewModel.isAktivitasLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(color = mRedMain)
        }
    } else
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Rekomendasi Aktivitas $tingkat_aktivitas Hari ini",
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
                    .fillMaxWidth(),
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
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
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
                                aktivitasViewModel.dataAllAktivitas?.let { it1 ->
                                    items(it1.data) {
                                        CustomAktivitasCard(
                                            aktivitasViewModel = aktivitasViewModel,
                                            idItem = it.id,
                                            nama = it.nama_aktivitas,
                                            tingkatAktivitas = it.tingkat_aktivitas,
                                            durasi = it.durasi,
                                            kalori = it.kalori,
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // ROW BUTTON
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, bottom = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        // DATA
                        // Jumlah menit
                        Text(
                            text = "Jumlah Menit : ${aktivitasViewModel.dataAllAktivitas?.total_menit} Menit",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(bottom = 4.dp),
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.SemiBold,
                            color = mBlack
                        )

                        // Jumlah kalori
                        Text(
                            text = "Jumlah Kalori : ${aktivitasViewModel.dataAllAktivitas?.total_kalori} Cal",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(bottom = 8.dp),
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.SemiBold,
                            color = mBlack
                        )

                        // Reset Semua data
                        OutlinedButton(
                            onClick = {

                                val headerMap = mutableMapOf<String, String>()
                                headerMap["Accept"] = "application/json"
                                headerMap["Authorization"] = "Bearer ${tokenText.value}"

                                if (tingkat_aktivitas != null) {
                                    aktivitasViewModel.resetAllAktivitas(
                                        headerMap,
                                        tingkat_aktivitas,
                                        context
                                    )
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .padding(bottom = 8.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = mWhite,
                                contentColor = mRedMain,
                                disabledBackgroundColor = mLightGrayScale,
                                disabledContentColor = mBlack
                            ),
                            border = BorderStroke(width = 1.dp, color = mRedMain),
                            shape = RoundedCornerShape(32.dp),
                        ) {
                            Text(
                                text = "Reset semua data",
                                style = MaterialTheme.typography.body1,
                                fontWeight = FontWeight.SemiBold,
                                color = mRedMain
                            )
                        }

                        // Button Tambah ke Diary
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
fun CustomAktivitasCard(
    modifier: Modifier = Modifier,
    idItem: Int,
    nama: String,
    tingkatAktivitas: String,
    durasi: String,
    kalori: String,
    aktivitasViewModel: AktivitasViewModel,
) {
    val context = LocalContext.current
    val store = CustomDataStore(context)
    val tokenText = store.getAccessToken.collectAsState(initial = "")

    val scope = rememberCoroutineScope()
    // sheet state
    val sheetEditDurasi = com.dokar.sheets.rememberBottomSheetState()

    // textfield controller
    val durasicontroller = rememberSaveable() { mutableStateOf("") }
    val beratBadan = rememberSaveable() { mutableStateOf("") }


    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(
            modifier = modifier.height(70.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier.weight(4f),
            ) {
                Text(
                    text = nama,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.SemiBold,
                    color = mBlack
                )
                Text(
                    text = "Intensitas aktivitas : $tingkatAktivitas",
                    modifier = modifier.padding(bottom = 3.dp),
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Normal,
                    color = mBlack
                )
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "$durasi Menit",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Normal,
                        color = mRedMain
                    )
                    IconButton(onClick = {
                        scope.launch {
                            sheetEditDurasi.expand()
                        }
                    }) {
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
                text = "$kalori Cal",
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

    // ======= BOTTOM SHEET =======
    CustomBottomSheet(
        state = sheetEditDurasi,
        isEnable = durasicontroller.value.isNotEmpty() && beratBadan.value.isNotEmpty(),
        textFieldTitle = "Durasi Aktivitas",
        onClick = {
            val headerMap = mutableMapOf<String, String>()
            headerMap["Accept"] = "application/json"
            headerMap["Authorization"] = "Bearer ${tokenText.value}"

            val data = DataUbahDurasiAktivitasModel(durasicontroller.value, beratBadan.value)

            aktivitasViewModel.ubahDurasiAktivitas(
                idItem.toString(),
                data,
                headerMap,
                tingkatAktivitas,
                context
            )
        },
        body = {
            // DURASI
            Text(
                text = "Durasi (Menit)",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                color = mGrayScale
            )
            CustomInputField(
                valueState = durasicontroller,
                placeholder = "12 Menit",
                trailingIcon = null,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            )

            // BERAT BADAN
            Text(
                text = "Berat badan (kg)",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                color = mGrayScale
            )
            CustomInputField(
                valueState = beratBadan,
                placeholder = "Masukan berat badan",
                trailingIcon = null,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            )
        }
    )
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun AktivitasScreenPreview() {
//    AktivitasScreen()
}