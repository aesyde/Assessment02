package com.fahrulredho0018.assessment02.navigation

import com.fahrulredho0018.assessment02.screen.KEY_ID_BUKU

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object FormBaru: Screen("detailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_BUKU}") {
        fun withId(id: Long) = "detailScreen/$id"
    }
    data object TrashScreen: Screen("trashScreen")
}