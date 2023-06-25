package com.example.e_medib.features.home_feature.view

import CustomBottomSheet
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.FileUpload
import androidx.compose.material.icons.rounded.UploadFile
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.e_medib.R
import com.example.e_medib.features.aktivitas_feature.view_model.AktivitasViewModel
import com.example.e_medib.features.home_feature.model.catatan.DataCatatanModel
import com.example.e_medib.features.home_feature.view_model.HomeViewModel
import com.example.e_medib.ui.theme.*
import com.example.e_medib.utils.CustomDataStore
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun DiaryScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    aktivitasViewModel: AktivitasViewModel = hiltViewModel()
) {
    val mExpanded = remember { mutableStateOf(false) }
    val jenisLuka = listOf("Luka Basah", "Luka Kering")
    val selectedLuka = remember { mutableStateOf("") }
    val mTextFieldSize = remember { mutableStateOf(Size.Zero) }
    val icon = if (mExpanded.value) Icons.Filled.KeyboardArrowUp
    else Icons.Filled.KeyboardArrowDown

    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    val store = CustomDataStore(context)
    val tokenText = store.getAccessToken.collectAsState(initial = "")

    // sheet state
    val sheetTambahDiary = com.dokar.sheets.rememberBottomSheetState()
    val sheetTambahCatatan = com.dokar.sheets.rememberBottomSheetState()

    val mContext = LocalContext.current

    // textfield controller
    val tambahDiaryState = rememberSaveable() { mutableStateOf("") }
    val gambarLukaFile = rememberSaveable() { mutableStateOf("") }
    val catatanLuka = rememberSaveable() { mutableStateOf("") }
    val catatanLainnya = rememberSaveable() { mutableStateOf("") }
    val isReadOnly = rememberSaveable() { mutableStateOf(false) }

    LaunchedEffect(Unit, block = {

    })

    if (homeViewModel.isLoading) {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(color = mRedMain)
        }
    } else Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        // TITLE DIARY
        Text(
            text = "Buat catatan laporan harian anda",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = mGrayScale
        )
        DiaryBox(
            header = {
                Text(
                    text = "Tambah catatan laporan harian",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = mGrayScale
                )
            },
            body = {
                Box(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(color = mLightGrayScale)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pen_tool),
                        contentDescription = "pent_tool",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(24.dp)
                    )
                }
            },
            footer = {
                Text(
                    text = "Tambahkan catatan laporan harian untuk mengetahui riwayat kesehatan anda.",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(bottom = 24.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = mGrayScale
                )
            },
            buttonLabel = "Tambah Diary",
            onButtonTap = {
                scope.launch {
                    sheetTambahDiary.expand()
                }
            },
        )

        // CATATAN LUKA
        Text(
            text = "Catatan Luka",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 16.dp, top = 20.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = mGrayScale
        )
        DiaryBox(
            header = {
                Text(
                    text = "Tambah catatan luka",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = mGrayScale
                )
            },
            body = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    LazyColumn() {
                        items(homeViewModel.allCatatanData.data) {
                            Card(elevation = 2.dp,
                                border = BorderStroke(0.5.dp, mLightGrayScale),
                                modifier = Modifier
                                    .clickable {
                                        selectedLuka.value = it.jenis_luka
                                        catatanLuka.value = it.catatan_luka
                                        catatanLainnya.value = it.catatan
                                        isReadOnly.value = true
                                        scope.launch {
                                            sheetTambahCatatan.expand()
                                        }
                                    }
                                    .padding(vertical = 8.dp)
                                    .fillMaxWidth()) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(
                                        text = "Tanggal : ${it.created_at}",
                                        style = MaterialTheme.typography.body1,
                                        fontWeight = FontWeight.Normal,
                                        color = mBlack
                                    )
                                    Text(
                                        text = "Jenis Luka : ${it.jenis_luka}",
                                        style = MaterialTheme.typography.body1,
                                        fontWeight = FontWeight.Normal,
                                        color = mBlack
                                    )
                                    Text(
                                        text = "Catatan Luka : ${it.catatan_luka}",
                                        style = MaterialTheme.typography.body1,
                                        fontWeight = FontWeight.Normal,
                                        color = mBlack
                                    )
                                    Text(
                                        text = "Catatan : ${it.catatan}",
                                        style = MaterialTheme.typography.body1,
                                        fontWeight = FontWeight.Normal,
                                        color = mBlack
                                    )
                                }
                            }
                        }
                    }
                }

            },
            footer = {},
            buttonLabel = "Tambah Catatan",
            onButtonTap = {
                selectedLuka.value = ""
                catatanLuka.value = ""
                catatanLainnya.value = ""
                isReadOnly.value = false
                scope.launch {
                    sheetTambahCatatan.expand()
                }
            },
        )
        Spacer(modifier = Modifier.height(32.dp))
    }

    // ======= BOTTOM SHEET =======
    // sheet tambah diary
    CustomBottomSheet(state = sheetTambahDiary,
        isEnable = tambahDiaryState.value.isNotEmpty(),
        textFieldTitle = "Tambah Diary",
        onClick = {

        },
        body = {
            // Gula Darah dan keterangan
            Text(
                text = "Gula Darah",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                color = mGrayScale
            )
            OutlinedTextField(
                value = "- mg/dL." +
                        " Keterangan :- ",
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth(),
                readOnly = true,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = mWhite,
                    unfocusedBorderColor = mLightGrayScale,
                    focusedBorderColor = mLightGrayScale
                ),
            )

            // Kolesterol  dan keterangan
            Text(
                text = "Kolesterol",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                color = mGrayScale
            )
            OutlinedTextField(
                value = "- mg/dL." +
                        " Keterangan :- ",
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth(),
                readOnly = true,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = mWhite,
                    unfocusedBorderColor = mLightGrayScale,
                    focusedBorderColor = mLightGrayScale
                ),
            )

            // Catatan  Luka
            Text(
                text = "Catatan Luka",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                color = mGrayScale
            )
            OutlinedTextField(
                value = "-",
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth(),
                readOnly = true,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = mWhite,
                    unfocusedBorderColor = mLightGrayScale,
                    focusedBorderColor = mLightGrayScale
                ),
            )

            // Total Kalori Konsumsi
            Text(
                text = "Total Kalori Konsumsi",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                color = mGrayScale
            )
            OutlinedTextField(
                value = "-",
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth(),
                readOnly = true,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = mWhite,
                    unfocusedBorderColor = mLightGrayScale,
                    focusedBorderColor = mLightGrayScale
                ),
            )

            // Total Kalori Aktivitas
            Text(
                text = "Total Kalori Aktivitas",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                color = mGrayScale
            )
            OutlinedTextField(
                value = "- Cal",
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth(),
                readOnly = true,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = mWhite,
                    unfocusedBorderColor = mLightGrayScale,
                    focusedBorderColor = mLightGrayScale
                ),
            )

            // Catatan
            Text(
                text = "Catatan Lainnya",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                color = mGrayScale
            )
            OutlinedTextField(
                value = "-",
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth(),
                readOnly = true,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = mWhite,
                    unfocusedBorderColor = mLightGrayScale,
                    focusedBorderColor = mLightGrayScale
                ),
            )

        })

    // sheet tambah catatan
    CustomBottomSheet(state = sheetTambahCatatan,
        isEnable = selectedLuka.value.isNotEmpty() && catatanLainnya.value.isNotEmpty() && catatanLuka.value.isNotEmpty() && !isReadOnly.value,
        textFieldTitle = "Tambah Catatan",
        onClick = {
            val headerMap = mutableMapOf<String, String>()
            headerMap["Accept"] = "application/json"
            headerMap["Authorization"] = "Bearer ${tokenText.value}"

            val dataCatatanModel = DataCatatanModel(
                jenis_luka = selectedLuka.value,
                catatan_luka = catatanLuka.value,
                catatan = catatanLainnya.value
            )

            homeViewModel.tambahCatatan(dataCatatanModel, headerMap, mContext)
            scope.launch {
                sheetTambahCatatan.collapse()
            }
        },
        body = {
            // ADD IMAGE FILE
            if (!isReadOnly.value) Column() {
                Text(
                    text = "Gambar Luka",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp, top = 16.dp),
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    color = mGrayScale
                )
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    OutlinedTextField(
                        value = gambarLukaFile.value,
                        onValueChange = { gambarLukaFile.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned { coordinates ->
                                mTextFieldSize.value = coordinates.size.toSize()
                            },
                        placeholder = {
                            Text(
                                text = "Gambar Luka (Opsional)",
                                style = MaterialTheme.typography.body1,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Normal,
                                color = mBlack
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = {
                                // upload imagr
                            }) {
                                Icon(
                                    Icons.Outlined.FileUpload,
                                    "FileUpload",
                                    tint = mRedMain,
                                )
                            }

                        },
                        readOnly = true,
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = mWhite,
                            textColor = mBlack,
                            unfocusedBorderColor = mLightGrayScale,
                            focusedBorderColor = mLightGrayScale
                        ),
                    )
                }
            }

            // JENIS LUKA
            Text(
                text = "Jenis Luka",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                color = mGrayScale
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                OutlinedTextField(
                    value = selectedLuka.value,
                    onValueChange = { selectedLuka.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            mTextFieldSize.value = coordinates.size.toSize()
                        },
                    placeholder = {
                        Text(
                            text = "Jenis Luka",
                            style = MaterialTheme.typography.body1,
//                            modifier = Modifier.padding(6.dp),
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Normal,
                            color = mBlack
                        )
                    },
                    trailingIcon = {
                        if (!isReadOnly.value) Icon(
                            icon,
                            "contentDescription",
                            tint = mBlack,
                            modifier = Modifier.clickable {
                                mExpanded.value = !mExpanded.value
                            },
                        )
                    },
                    readOnly = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = mWhite,
                        textColor = mBlack,
                        unfocusedBorderColor = mLightGrayScale,
                        focusedBorderColor = mLightGrayScale
                    ),
                )
            }
            DropdownMenu(
                expanded = mExpanded.value,
                onDismissRequest = { mExpanded.value = false },
                modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.value.width.toDp() })
            ) {
                jenisLuka.forEach { label ->
                    DropdownMenuItem(onClick = {
                        selectedLuka.value = label
                        mExpanded.value = false
                    }) {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Normal,
                            color = mBlack
                        )
                    }
                }
            }

            // CATATAN LUKA
            Text(
                text = "Catatan Luka",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                color = mGrayScale
            )
            OutlinedTextField(
                value = catatanLuka.value,
                onValueChange = { catatanLuka.value = it },
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                ),
                readOnly = isReadOnly.value,
                placeholder = {
                    Text(
                        text = "Tambahkan catatan luka...",
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(6.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Light,
                        color = mGrayScale
                    )
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = mWhite,
                    unfocusedBorderColor = mLightGrayScale,
                    focusedBorderColor = mLightGrayScale
                ),
            )

            // CATATAN LAINNYA
            Text(
                text = "Tambah Catatan",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                color = mGrayScale
            )
            OutlinedTextField(
                value = catatanLainnya.value,
                onValueChange = { catatanLainnya.value = it },
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                ),
                readOnly = isReadOnly.value,
                placeholder = {
                    Text(
                        text = "Tambahkan catatan lainnya...",
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(6.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Light,
                        color = mGrayScale
                    )
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = mWhite,
                    unfocusedBorderColor = mLightGrayScale,
                    focusedBorderColor = mLightGrayScale
                ),
            )

        })
}


@Composable
fun DiaryBox(
    header: @Composable @UiComposable () -> Unit,
    body: @Composable @UiComposable () -> Unit,
    footer: @Composable @UiComposable () -> Unit,
    buttonLabel: String,
    onButtonTap: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, color = mLightGrayScale),
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // header
            header()

            // body
            body()

            // footer
            footer()

            // button
            Button(
                onClick = {
                    onButtonTap()
                },
                modifier = Modifier
                    .padding(top = 16.dp)
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
                    text = buttonLabel,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.SemiBold,
                    color = mWhite
                )
            }

        }

    }

}


@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun DiaryScreenPreview() {
    DiaryScreen()
}