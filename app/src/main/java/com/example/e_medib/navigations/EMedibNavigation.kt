package com.example.e_medib.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.e_medib.features.aktivitas_feature.view.AktivitasScreen
import com.example.e_medib.features.auth_feature.view.LoginScreen
import com.example.e_medib.features.auth_feature.view.RegisterScreen
import com.example.e_medib.features.home_feature.view.HomeScreen
import com.example.e_medib.features.pantau_kalori_feature.view.PantauKaloriScreen
import com.example.e_medib.features.pantau_kalori_feature.view.SearchMenuScreen
import com.example.e_medib.features.profile_feature.view.ProfileScreen
import com.example.e_medib.features.splash_screen_feature.view.SplashScreen

@Composable
fun EMedibNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = EMedibAppScreen.SplashScreen.name) {
        // DEFINE ALL POSSIBLE SCREEN THAT APP WILL HAVE


        composable(EMedibAppScreen.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(EMedibAppScreen.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        composable(EMedibAppScreen.LoginScreen.name) {
            LoginScreen(navController = navController)
        }

        composable(EMedibAppScreen.RegisterScreen.name) {
            RegisterScreen(navController = navController)
        }

        composable(EMedibAppScreen.AktivitasScreen.name) {
            AktivitasScreen(navController = navController)
        }
        composable(EMedibAppScreen.SearchMenuScreen.name) {
            SearchMenuScreen(navController = navController)
        }

        composable(EMedibAppScreen.PantauKaloriScreen.name) {
            PantauKaloriScreen(navController = navController)
        }

        composable(EMedibAppScreen.ProfileScreen.name) {
            ProfileScreen(navController = navController)
        }

    }
}