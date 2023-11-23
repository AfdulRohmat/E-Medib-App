package com.example.e_medib

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.e_medib.navigations.AppScreen
import com.example.e_medib.navigations.NavigationGraph
import com.example.e_medib.ui.theme.EMedibTheme
import com.example.e_medib.ui.theme.mGrayScale
import com.example.e_medib.ui.theme.mRedMain
import com.example.e_medib.ui.theme.mWhite
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EMedibTheme {
                MainScreenView()
            }
        }

        // SETTING FOR MANAGE SCROLL SCREEN WHEN KEYBOARD POP UP
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom = bottom)

            insets
        }
    }
}


@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun MainScreenView() {
    val navController = rememberNavController()
    val onBack: () -> Unit = {
        navController.popBackStack()
    }
    val screens = listOf(
        AppScreen.Beranda,
        AppScreen.PantauKalori,
        AppScreen.PilihKategoriAktivitasScreen,
        AppScreen.Profil,
    )

    val showBottomBar = navController
        .currentBackStackEntryAsState().value?.destination?.route in screens.map { it.screen_route }


    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigation(backgroundColor = mWhite, contentColor = mGrayScale) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    screens.forEach { screen ->
                        val selected = currentRoute == screen.screen_route;

                        BottomNavigationItem(
                            selected = currentRoute == screen.screen_route,
                            icon = {
                                screen.icon?.let {
                                    Row(horizontalArrangement = Arrangement.Center) {
                                        Icon(
                                            imageVector = it,
                                            contentDescription = "icon"
                                        )
                                    }
                                }
                            },
                            label = {
                                Row(horizontalArrangement = Arrangement.Center) {
                                    androidx.compose.material3.Text(
                                        text = screen.title,
                                        style = MaterialTheme.typography.caption,
                                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Light,
                                        color = if (selected) mRedMain else mGrayScale
                                    )
                                }

                            },
                            selectedContentColor = mRedMain,
                            unselectedContentColor = mGrayScale,
                            alwaysShowLabel = true,
                            onClick = {
                                navController.navigate(screen.screen_route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                        )
                    }


                }
            }
        }
    ) {
        NavigationGraph(navController = navController)
    }
}

