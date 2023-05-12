package com.example.e_medib.navigations

enum class EMedibAppScreen {
    SplashScreen,
    HomeScreen,
    LoginScreen,
    RegisterScreen,
    AktivitasScreen,
    PantauKaloriScreen,
    SearchMenuScreen,
    ProfileScreen,
    MainScreen,
    PilihAktivitasScreen,
    DetailAktivitasScreen,

    ;

    companion object {
        fun fromRoute(route: String?): EMedibAppScreen = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            HomeScreen.name -> HomeScreen
            LoginScreen.name -> LoginScreen
            RegisterScreen.name -> RegisterScreen
            AktivitasScreen.name -> AktivitasScreen
            PilihAktivitasScreen.name -> PilihAktivitasScreen
            DetailAktivitasScreen.name -> DetailAktivitasScreen
            PantauKaloriScreen.name -> PantauKaloriScreen
            SearchMenuScreen.name -> SearchMenuScreen
            ProfileScreen.name -> ProfileScreen
            MainScreen.name -> MainScreen
            null -> HomeScreen
            else -> throw java.lang.IllegalArgumentException("Route $route is not defined")
        }
    }
}

