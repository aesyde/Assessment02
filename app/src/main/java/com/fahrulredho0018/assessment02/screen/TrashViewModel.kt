package com.fahrulredho0018.assessment02.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahrulredho0018.assessment02.database.PenulisDao
import com.fahrulredho0018.assessment02.model.Penulis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TrashViewModel(private val dao: PenulisDao) : ViewModel() {

    val data: StateFlow<List<Penulis>> = dao.getDeletedPenulis().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    fun pulihkan(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.restore(id)
        }
    }

    fun hapusPermanen(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}