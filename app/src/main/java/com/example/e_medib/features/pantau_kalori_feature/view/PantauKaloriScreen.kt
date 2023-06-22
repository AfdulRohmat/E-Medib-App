package com.example.e_medib.features.pantau_kalori_feature.view

import CustomBottomSheet
import CustomExpandedCard
import CustomInputField
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.e_medib.R
import com.example.e_medib.features.pantau_kalori_feature.model.DataKonsumsiMakananModel
import com.example.e_medib.features.pantau_kalori_feature.view_model.PantauKaloriViewModel
import com.example.e_medib.features.profile_feature.view_model.ProfileViewModel
import com.example.e_medib.ui.theme.*
import com.example.e_medib.utils.CustomDataStore
import com.foreverrafs.datepicker.DatePickerTimeline
import com.foreverrafs.datepicker.state.rememberDatePickerState
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun PantauKaloriScreen(
    navController: NavController,
    pantauKaloriViewModel: PantauKaloriViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val datePickerState = rememberDatePickerState(initialDate = LocalDate.now())
    val pilihHari = rememberSaveable() { mutableStateOf("${LocalDate.now()}") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val store = CustomDataStore(context)
    val tokenText = store.getAccessToken.collectAsState(initial = "")

    val headerMap = mutableMapOf<String, String>()
    headerMap["Accept"] = "application/json"
    headerMap["Authorization"] = "Bearer ${tokenText.value}"

    // sheet state
    val sheetSarapan = com.dokar.sheets.rememberBottomSheetState()
    val sheetMakanSiang = com.dokar.sheets.rememberBottomSheetState()
    val sheetMakanMalam = com.dokar.sheets.rememberBottomSheetState()
    val sheetLainnya = com.dokar.sheets.rememberBottomSheetState()

    // textfield controller
    val makanan = rememberSaveable() { mutableStateOf("") }
    val porsi = rememberSaveable() { mutableStateOf("") }
    val kalori = rememberSaveable() { mutableStateOf("") }

    LaunchedEffect(Unit, block = {
        val headerMap = mutableMapOf<String, String>()
        headerMap["Accept"] = "application/json"
        headerMap["Authorization"] = "Bearer ${tokenText.value}"
        pantauKaloriViewModel.getSarapan(headerMap, pilihHari.value)
        pantauKaloriViewModel.getMakanSiang(headerMap, pilihHari.value)
        pantauKaloriViewModel.getMakanMalam(headerMap, pilihHari.value)
        pantauKaloriViewModel.getKonsumsiLainnya(headerMap, pilihHari.value)
        profileViewModel.getAllBMR(headerMap)
    })
    Scaffold(topBar = {
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
    }) {
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
                    pilihHari.value = selectedDate.toString()
                    val headerMap = mutableMapOf<String, String>()
                    headerMap["Accept"] = "application/json"
                    headerMap["Authorization"] = "Bearer ${tokenText.value}"
                    pantauKaloriViewModel.getSarapan(headerMap, pilihHari.value)
                    pantauKaloriViewModel.getMakanSiang(headerMap, pilihHari.value)
                    pantauKaloriViewModel.getMakanMalam(headerMap, pilihHari.value)
                    pantauKaloriViewModel.getKonsumsiLainnya(headerMap, pilihHari.value)
                },
            )

            if (pantauKaloriViewModel.isLoading) {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(color = mRedMain)
                }
            } else
            // COLUMN LAYOUT
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 60.dp)
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
                                            text = "${pantauKaloriViewModel.allKonsumsiSarapan.total_kalori_sarapan}",
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
                                    scope.launch {
                                        sheetSarapan.expand()
                                    }
                                    // navController.navigate(AppScreen.SearchMenuScreen.screen_route)
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
                            if (pantauKaloriViewModel.allKonsumsiSarapan.data.isEmpty()) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Tidak ada data",
                                        style = MaterialTheme.typography.body1,
                                        fontWeight = FontWeight.SemiBold,
                                        color = mGrayScale
                                    )
                                }
                            } else Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                            ) {
                                LazyColumn() {
                                    items(pantauKaloriViewModel.allKonsumsiSarapan.data) {
                                        Card(
                                            elevation = 2.dp,
                                            modifier = Modifier
                                                .padding(vertical = 8.dp)
                                                .fillMaxWidth()
                                        ) {
                                            Column(modifier = Modifier.padding(16.dp)) {
                                                Text(
                                                    text = "Tanggal : ${it.created_at}",
                                                    style = MaterialTheme.typography.body1,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = mGrayScale
                                                )
                                                Text(
                                                    text = "Makanan : ${it.makanan}",
                                                    style = MaterialTheme.typography.body1,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = mGrayScale
                                                )
                                                Text(
                                                    text = "Porsi : ${it.porsi} porsi",
                                                    style = MaterialTheme.typography.body1,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = mGrayScale
                                                )
                                                Text(
                                                    text = "Kalori : ${it.kalori} gram",
                                                    style = MaterialTheme.typography.body1,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = mGrayScale
                                                )
                                                Text(
                                                    text = "Waktu Makan : ${it.jenis_waktu_makan}",
                                                    style = MaterialTheme.typography.body1,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = mGrayScale
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        },
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
                                            text = "${pantauKaloriViewModel.allKonsumsiMakanSiang.total_kalori_makan_siang}",
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
                                    scope.launch {
                                        sheetMakanSiang.expand()
                                    }
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
                            if (pantauKaloriViewModel.allKonsumsiMakanSiang.data.isEmpty()) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Tidak ada data",
                                        style = MaterialTheme.typography.body1,
                                        fontWeight = FontWeight.SemiBold,
                                        color = mGrayScale
                                    )
                                }
                            } else Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                            ) {
                                LazyColumn() {
                                    items(pantauKaloriViewModel.allKonsumsiMakanSiang.data) {
                                        Card(
                                            elevation = 2.dp,
                                            modifier = Modifier
                                                .padding(vertical = 8.dp)
                                                .fillMaxWidth()
                                        ) {
                                            Column(modifier = Modifier.padding(16.dp)) {
                                                Text(
                                                    text = "Tanggal : ${it.created_at}",
                                                    style = MaterialTheme.typography.body1,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = mGrayScale
                                                )
                                                Text(
                                                    text = "Makanan : ${it.makanan}",
                                                    style = MaterialTheme.typography.body1,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = mGrayScale
                                                )
                                                Text(
                                                    text = "Porsi : ${it.porsi} porsi",
                                                    style = MaterialTheme.typography.body1,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = mGrayScale
                                                )
                                                Text(
                                                    text = "Kalori : ${it.kalori} gram",
                                                    style = MaterialTheme.typography.body1,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = mGrayScale
                                                )
                                                Text(
                                                    text = "Waktu Makan : ${it.jenis_waktu_makan}",
                                                    style = MaterialTheme.typography.body1,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = mGrayScale
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        },
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
                                            text = "${pantauKaloriViewModel.allKonsumsiMakanMalam.total_kalori_makan_malam}",
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
                                    scope.launch {
                                        sheetMakanMalam.expand()
                                    }
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
                            if (pantauKaloriViewModel.allKonsumsiMakanMalam.data.isEmpty()) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Tidak ada data",
                                        style = MaterialTheme.typography.body1,
                                        fontWeight = FontWeight.SemiBold,
                                        color = mGrayScale
                                    )
                                }
                            } else Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                            ) {
                                LazyColumn() {
                                    items(pantauKaloriViewModel.allKonsumsiMakanMalam.data) {
                                        Card(
                                            elevation = 2.dp,
                                            modifier = Modifier
                                                .padding(vertical = 8.dp)
                                                .fillMaxWidth()
                                        ) {
                                            Column(modifier = Modifier.padding(16.dp)) {
                                                Text(
                                                    text = "Tanggal : ${it.created_at}",
                                                    style = MaterialTheme.typography.body1,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = mGrayScale
                                                )
                                                Text(
                                                    text = "Makanan : ${it.makanan}",
                                                    style = MaterialTheme.typography.body1,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = mGrayScale
                                                )
                                                Text(
                                                    text = "Porsi : ${it.porsi} porsi",
                                                    style = MaterialTheme.typography.body1,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = mGrayScale
                                                )
                                                Text(
                                                    text = "Kalori : ${it.kalori} gram",
                                                    style = MaterialTheme.typography.body1,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = mGrayScale
                                                )
                                                Text(
                                                    text = "Waktu Makan : ${it.jenis_waktu_makan}",
                                                    style = MaterialTheme.typography.body1,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = mGrayScale
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        },
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
                                            text = "${pantauKaloriViewModel.allKonsumsiLainnya.total_kalori_lainnya}",
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
                                    scope.launch {
                                        sheetLainnya.expand()
                                    }
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
                            if (pantauKaloriViewModel.allKonsumsiLainnya.data.isEmpty()) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Tidak ada data",
                                        style = MaterialTheme.typography.body1,
                                        fontWeight = FontWeight.SemiBold,
                                        color = mGrayScale
                                    )
                                }
                            } else Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                            ) {
                                LazyColumn() {
                                    items(pantauKaloriViewModel.allKonsumsiLainnya.data) {
                                        Card(
                                            elevation = 2.dp,
                                            modifier = Modifier
                                                .padding(vertical = 8.dp)
                                                .fillMaxWidth()
                                        ) {
                                            Column(modifier = Modifier.padding(16.dp)) {
                                                Text(
                                                    text = "Tanggal : ${it.created_at}",
                                                    style = MaterialTheme.typography.body1,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = mGrayScale
                                                )
                                                Text(
                                                    text = "Makanan : ${it.makanan}",
                                                    style = MaterialTheme.typography.body1,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = mGrayScale
                                                )
                                                Text(
                                                    text = "Porsi : ${it.porsi} porsi",
                                                    style = MaterialTheme.typography.body1,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = mGrayScale
                                                )
                                                Text(
                                                    text = "Kalori : ${it.kalori} gram",
                                                    style = MaterialTheme.typography.body1,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = mGrayScale
                                                )
                                                Text(
                                                    text = "Waktu Makan : ${it.jenis_waktu_makan}",
                                                    style = MaterialTheme.typography.body1,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = mGrayScale
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        },
                    )


                    // TOTAL KALORI
                    CustomExpandedCard(
                        modifier = Modifier.padding(top = 16.dp),
                        isExpanded = false,
                        header = {
                            Column() {
                                Text(
                                    text = "Total Kalori",
                                    style = MaterialTheme.typography.body1,
                                    fontWeight = FontWeight.Bold,
                                    color = mBlack
                                )
                                // STATUS
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = "${pantauKaloriViewModel.allKonsumsiSarapan.total_kalori}",
                                        style = MaterialTheme.typography.h1,
                                        fontWeight = FontWeight.SemiBold,
                                        color = mRedMain
                                    )
                                    Text(
                                        text = " / ${profileViewModel.recentBMRData.bmr} Cal",
                                        style = MaterialTheme.typography.h1,
                                        fontWeight = FontWeight.SemiBold,
                                        color = mBlack
                                    )
                                }
                            }
                        },
                        body = { },
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }
        }


        // ======= BOTTOM SHEET =======
        ShowCustomBottomSheet(
            state = sheetSarapan,
            isEnable = makanan.value.isNotEmpty() && porsi.value.isNotEmpty() && kalori.value.isNotEmpty(),
            pilihHari.value,
            makanan,
            porsi,
            kalori,
            jenisWaktuMakan = "SARAPAN",
        )

        ShowCustomBottomSheet(
            state = sheetMakanSiang,
            isEnable = makanan.value.isNotEmpty() && porsi.value.isNotEmpty() && kalori.value.isNotEmpty(),
            pilihHari.value,
            makanan,
            porsi,
            kalori,
            jenisWaktuMakan = "MAKAN_SIANG",
        )

        ShowCustomBottomSheet(
            state = sheetMakanMalam,
            isEnable = makanan.value.isNotEmpty() && porsi.value.isNotEmpty() && kalori.value.isNotEmpty(),
            pilihHari.value,
            makanan,
            porsi,
            kalori,
            jenisWaktuMakan = "MAKAN_MALAM",
            pantauKaloriViewModel,
        )

        ShowCustomBottomSheet(
            state = sheetLainnya,
            isEnable = makanan.value.isNotEmpty() && porsi.value.isNotEmpty() && kalori.value.isNotEmpty(),
            pilihHari.value,
            makanan,
            porsi,
            kalori,
            jenisWaktuMakan = "LAINNYA",
        )

    }


}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowCustomBottomSheet(
    state: com.dokar.sheets.BottomSheetState,
    isEnable: Boolean,
    pilihHari: String,
    makanan: MutableState<String>,
    porsi: MutableState<String>,
    kalori: MutableState<String>,
    jenisWaktuMakan: String,
    pantauKaloriViewModel: PantauKaloriViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    val store = CustomDataStore(context)
    val tokenText = store.getAccessToken.collectAsState(initial = "")

    val headerMap = mutableMapOf<String, String>()
    headerMap["Accept"] = "application/json"
    headerMap["Authorization"] = "Bearer ${tokenText.value}"

    CustomBottomSheet(state = state,
        isEnable = isEnable,
        textFieldTitle = "Data konsumsi Makanan",
        onClick = {
            val konsumsiMakananData = DataKonsumsiMakananModel(
                makanan = makanan.value,
                porsi = porsi.value,
                kalori = kalori.value,
                jenisWaktuMakan
            )
            pantauKaloriViewModel.tambahKonsumsiMakanan(
                waktuMakan = jenisWaktuMakan,
                tanggal = pilihHari,
                konsumsiMakananData,
                headerMap
            )
            makanan.value = ""
            porsi.value = ""
            kalori.value = ""
            scope.launch {
                state.collapse()
            }

        },
        body = {
            // TEXT FIELD
            Text(
                text = "Makanan",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                color = mGrayScale
            )
            CustomInputField(
                valueState = makanan,
                placeholder = "Masukan makanan",
                trailingIcon = null,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            )

            Text(
                text = "Porsi (dalam satuan)",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                color = mGrayScale
            )
            CustomInputField(
                valueState = porsi,
                placeholder = "Masukan porsi makanan (conton: 1, 2, ..)",
                trailingIcon = null,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            )

            Text(
                text = "Kalori (dalam Cal)",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                color = mGrayScale
            )
            CustomInputField(
                valueState = kalori,
                placeholder = "Masukan kalori makanan (dalam cal)",
                trailingIcon = null,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            )
        })
}


@Composable
fun RowItem(
    title: String, portion: String, calories: String, onClick: () -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
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