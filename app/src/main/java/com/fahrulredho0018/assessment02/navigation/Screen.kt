package com.fahrulredho0018.assessment02.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object FormBaru: Screen("detailScreen")
}