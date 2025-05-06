package com.fahrulredho0018.assessment02.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahrulredho0018.assessment02.database.PenulisDao
import com.fahrulredho0018.assessment02.model.Penulis
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(dao: PenulisDao) : ViewModel() {

    val data: StateFlow<List<Penulis>> = dao.getPenulis().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )
}