package com.example.e_medib

import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.e_medib.navigations.AppScreen
import com.example.e_medib.ui.theme.mGrayScale
import com.example.e_medib.ui.theme.mRedMain
import com.example.e_medib.ui.theme.mWhite


@Composable
fun AppBottomNavigation(navController: NavController) {
    val screens = listOf(
        AppScreen.Beranda,
        AppScreen.PantauKalori,
        AppScreen.Aktivitas,
        AppScreen.Profil,

        )

    BottomNavigation(backgroundColor = mWhite, contentColor = mGrayScale) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->
            BottomNavigationItem(
                selected = currentRoute == screen.screen_route,
                icon = { screen.icon?.let { Icon(imageVector = it, contentDescription = "icon") } },
                label = {
                    Text(
                        text = screen.title,
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Light,
                    )
                },
                selectedContentColor = mRedMain,
                unselectedContentColor = mGrayScale,
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(screen.screen_route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }


    }
}