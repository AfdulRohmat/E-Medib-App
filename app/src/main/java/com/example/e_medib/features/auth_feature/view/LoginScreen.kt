package com.example.e_medib.features.auth_feature.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.e_medib.R
import com.example.e_medib.features.auth_feature.view.components.CustomInputField
import com.example.e_medib.navigations.EMedibAppScreen
import com.example.e_medib.ui.theme.*

@Composable
fun LoginScreen(navController: NavController) {
    Scaffold {
        LoginScreenComponent(navController, onDone = { username, password ->
            Log.d("Login", "$username || $password")
        })
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreenComponent(
    navController: NavController,
    onDone: (String, String) -> Unit = { username, password -> }
) {
    val username = rememberSaveable() { mutableStateOf("") }
    val password = rememberSaveable() { mutableStateOf("") }
    val showPassword = rememberSaveable() { mutableStateOf(false) }
    val isLoading = remember() { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val isValidEmailOrPassword = remember(username.value, password.value) {
        username.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // WELCOME
        Image(
            painter = painterResource(id = R.drawable.app_logo_red),
            contentDescription = "app_logo", modifier = Modifier.size(60.dp)
        )
        Text(
            text = "Selamat Datang",
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            color = mRedMain
        )
        Text(
            text = "Silahkan masuk ke akun anda",
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.Normal,
            color = mRedMain
        )
        Spacer(modifier = Modifier.height(32.dp))

        // Username
        Text(
            text = "Username",
            modifier = Modifier
                .padding(bottom = 4.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.Normal,
            color = mBlack,
            textAlign = TextAlign.Left
        )
        CustomInputField(
            valueState = username, label = "username",
            imeAction = ImeAction.Next,
        )
        Spacer(modifier = Modifier.height(16.dp))

        // password
        Text(
            text = "Password",
            modifier = Modifier
                .padding(bottom = 4.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.Normal,
            color = mBlack,
            textAlign = TextAlign.Left
        )
        CustomInputField(
            valueState = password,
            passwordVisible = showPassword,
            isPassword = true,
            keyboardType = KeyboardType.Password,
            visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            imeAction = ImeAction.Done,
            label = "password",
            onAction = KeyboardActions {
                if (!isValidEmailOrPassword) return@KeyboardActions
                onDone(username.value, password.value)
                keyboardController?.hide()
            })
        Spacer(modifier = Modifier.height(32.dp))


        // button
        Button(
            onClick = {
                onDone(username.value.trim(), password.value.trim())
                keyboardController?.hide()
                navController.navigate(EMedibAppScreen.PantauKaloriScreen.name)
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
            enabled = !isLoading.value && isValidEmailOrPassword
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.SemiBold,
                color = mWhite
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        // go to register
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Belum punya akun ?",
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Normal,
                color = mBlack
            )
            TextButton(onClick = {
                navController.navigate(EMedibAppScreen.RegisterScreen.name)
            }) {
                Text(
                    text = "Daftar",
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.SemiBold,
                    color = mRedMain
                )
            }
        }
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun DefaultPreview() {
//    LoginScreenComponent()
}