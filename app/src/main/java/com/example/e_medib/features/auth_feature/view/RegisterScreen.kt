package com.example.e_medib.features.auth_feature.view

import CustomInputField
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_medib.features.aktivitas_feature.view.DetailAktivitasScreen
import com.example.e_medib.features.auth_feature.view.components.CustomLoginInputField
import com.example.e_medib.navigations.AppScreen
import com.example.e_medib.ui.theme.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(navController: NavController) {
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            val namaLengkap = rememberSaveable() { mutableStateOf("") }
            val username = rememberSaveable() { mutableStateOf("") }
            val nik = rememberSaveable() { mutableStateOf("") }
            val email = rememberSaveable() { mutableStateOf("") }
            val tempatTanggalLahir = rememberSaveable() { mutableStateOf("") }
            val jenisKelamin = rememberSaveable() { mutableStateOf("") }
            val tinggiBadan = rememberSaveable() { mutableStateOf("") }
            val beratBadan = rememberSaveable() { mutableStateOf("") }
            val jenisAlergi = rememberSaveable() { mutableStateOf("") }
            val riwayatPenyakit = rememberSaveable() { mutableStateOf("") }
            val password = rememberSaveable() { mutableStateOf("") }
            val repeatPassword = rememberSaveable() { mutableStateOf("") }

            val showPassword = rememberSaveable() { mutableStateOf(false) }
            val showRepeatPassword = rememberSaveable() { mutableStateOf(false) }
            val isLoading = remember() { mutableStateOf(false) }
            val keyboardController = LocalSoftwareKeyboardController.current
            val isValidInputs =
                remember(
                    namaLengkap.value,
                    username.value,
                    nik.value,
                    email.value,
                    tempatTanggalLahir.value,
                    jenisKelamin.value,
                    tinggiBadan.value,
                    beratBadan.value,
                    jenisAlergi.value,
                    riwayatPenyakit.value,
                    password.value,
                    repeatPassword.value,
                    ) {
                    namaLengkap.value.trim().isNotEmpty() && username.value.trim()
                        .isNotEmpty() && nik.value.trim().isNotEmpty() && email.value.trim()
                        .isNotEmpty() && tempatTanggalLahir.value.trim()
                        .isNotEmpty() && jenisKelamin.value.trim()
                        .isNotEmpty() && tinggiBadan.value.trim()
                        .isNotEmpty() && beratBadan.value.trim()
                        .isNotEmpty() && jenisAlergi.value.trim()
                        .isNotEmpty() && riwayatPenyakit.value.trim()
                        .isNotEmpty() && password.value.trim()
                        .isNotEmpty() && repeatPassword.value.trim().isNotEmpty()
                }


            // TITLE
            Text(
                text = "Daftar Akun",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = mRedMain
            )
            Text(
                text = "Masukkan data diri anda",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                color = mRedMain
            )


            // NAMA LENGKAP
            Text(
                text = "Nama Lengkap",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = mBlack
            )
            CustomInputField(
                valueState = namaLengkap,
                placeholder = "Angelina Jolie",
                trailingIcon = null,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            )


            // USERNAME
            Text(
                text = "Username",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = mBlack
            )
            CustomInputField(
                valueState = username,
                placeholder = "AngelinaJolie123",
                trailingIcon = null,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            )

            // NIK
            Text(
                text = "NIK",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = mBlack
            )
            CustomInputField(
                valueState = nik,
                placeholder = "01234567890",
                trailingIcon = null,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            )

            // EMAIL
            Text(
                text = "Email",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = mBlack
            )
            CustomInputField(
                valueState = email,
                placeholder = "Angelina12@gmail.com",
                trailingIcon = null,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
            )

            // TANGGAL LAHIR
            Text(
                text = "Tanggal Lahir",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = mBlack
            )
            CustomInputField(
                valueState = tempatTanggalLahir,
                placeholder = "12/12/2000",
                trailingIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Outlined.CalendarMonth,
                            contentDescription = "CalendarMonth"
                        )
                    }
                },
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            )

            // JENIS KELAMIN
            Text(
                text = "Jenis Kelamin",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = mBlack
            )
            CustomInputField(
                valueState = jenisKelamin,
                placeholder = "Jenis Kelamin",
                trailingIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Outlined.ExpandMore,
                            contentDescription = "ExpandMore"
                        )
                    }

                },
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            )

            // TINGGI BADAN && BERAT BADAN
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // TB
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = "Tinggi Badan",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = mBlack
                    )
                    CustomInputField(
                        valueState = tinggiBadan,
                        placeholder = "00",
                        trailingIcon = {
                            Text(
                                text = "cm",
                                style = MaterialTheme.typography.caption,
                                fontWeight = FontWeight.Bold,
                                color = mGrayScale
                            )
                        },
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next,
                    )

                }
                Spacer(modifier = Modifier.width(16.dp))

                // BB
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = "Berat Badan",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = mBlack
                    )
                    CustomInputField(
                        valueState = beratBadan,
                        placeholder = "00",
                        trailingIcon = {
                            Text(
                                text = "kg",
                                style = MaterialTheme.typography.caption,
                                fontWeight = FontWeight.Bold,
                                color = mGrayScale
                            )
                        },
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next,
                    )

                }

            }

            // JENIS ALERGI
            Text(
                text = "Jenis Alergi",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = mBlack
            )
            CustomInputField(
                valueState = jenisAlergi,
                placeholder = "Jenis Alergi",
                trailingIcon = null,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            )

            // RIWAYAT PENYAKIT
            Text(
                text = "Riwayat Penyakit",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = mBlack
            )
            CustomInputField(
                valueState = riwayatPenyakit,
                placeholder = "Riwayat Penyakit",
                trailingIcon = null,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            )

            // PASSWORD
            Text(
                text = "Password",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = mBlack
            )
            CustomLoginInputField(
                valueState = password,
                passwordVisible = showPassword,
                isPassword = true,
                keyboardType = KeyboardType.Password,
                visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                imeAction = ImeAction.Next,
                label = "Password",
            )

            // REPEAT PASSWORD
            Text(
                text = "Ulangi Password",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 16.dp),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = mBlack
            )
            CustomLoginInputField(
                valueState = repeatPassword,
                passwordVisible = showRepeatPassword,
                isPassword = true,
                keyboardType = KeyboardType.Password,
                visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                imeAction = ImeAction.Done,
                label = "Ulangi Password",
            )
            Spacer(modifier = Modifier.height(32.dp))

            // BUTTON
            Button(
                onClick = {
                    keyboardController?.hide()
                    // navController.navigate(AppScreen.Beranda.screen_route)
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
                enabled = !isLoading.value && isValidInputs
            ) {
                Text(
                    text = "Daftar",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.SemiBold,
                    color = mWhite
                )
            }

            // BALIK KE LOGIN
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sudah punya akun ?",
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Normal,
                    color = mBlack
                )
                TextButton(onClick = {
                    navController.popBackStack()
                }) {
                    Text(
                        text = "Masuk",
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.SemiBold,
                        color = mRedMain
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun RegisterScreenScreenPreview() {
    val navController = rememberNavController()

    RegisterScreen(navController)

}
