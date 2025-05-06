package com.fahrulredho0018.assessment02.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fahrulredho0018.assessment02.database.PenulisDb
import com.fahrulredho0018.assessment02.screen.DetailViewModel
import com.fahrulredho0018.assessment02.screen.MainViewModel

class ViewModelFactory ( private val context: Context ) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = PenulisDb.getInstance(context).dao
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
