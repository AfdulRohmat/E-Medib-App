package com.example.e_medib.features.splash_screen_feature.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_medib.R
import com.example.e_medib.navigations.AppScreen
import com.example.e_medib.ui.theme.mWhite
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {

    // DELAY FOR SEVERAL TIME THEN GO TO NEXT PAGE
    LaunchedEffect(key1 = true, block = {
        delay(500L)
        navController.navigate(AppScreen.LoginScreen.screen_route) {
//            popUpTo(AppScreen.Beranda.screen_route) {
//                inclusive = true
//            }
            popUpTo(AppScreen.LoginScreen.screen_route) {
                inclusive = true
            }
        }
    })

    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.primary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "app_logo", modifier = Modifier.size(100.dp)
            )
            Text(
                text = "E-Medib",
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.h1,
                fontWeight = FontWeight.Bold,
                color = mWhite
            )
            Text(
                text = "Electronic Diary for Diabetes",
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Normal,
                color = mWhite
            )
        }
    }
}
