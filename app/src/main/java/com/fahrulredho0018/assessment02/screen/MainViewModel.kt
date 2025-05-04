package com.fahrulredho0018.assessment02.screen

import androidx.lifecycle.ViewModel
import com.fahrulredho0018.assessment02.model.Penulis

class MainViewModel : ViewModel() {

    val data = listOf(
        Penulis(
            1,
            "Kuliah Mobpro 17 Feb",
            "Kuliah hari pertamaaa kuliahhhhhhhhhh",
            "2025-02-17 12:34:56"
        ),
        Penulis(
            2,
            "aku adalah ironmen",
            "Daffa",
            "2025-02-19 11:22:50"
        )
    )
}