package com.fahrulredho0018.assessment02.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahrulredho0018.assessment02.database.PenulisDao
import com.fahrulredho0018.assessment02.model.Penulis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailViewModel(private val dao: PenulisDao) : ViewModel() {
    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    fun insert(judul: String, isi: String, genre: String) {
        val penulis = Penulis(
            tanggal = formatter.format(Date()),
            judul = judul,
            nama = isi,
            kategori = genre
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(penulis)
        }
    }

    suspend fun getPenulis(id: Long): Penulis? {
        return dao.getPenulisById(id)
    }

    fun update(id: Long, judul: String, isi: String, genre: String) {
        val penulis = Penulis(
            id      = id,
            tanggal = formatter.format(Date()),
            judul   = judul,
            nama = isi,
            kategori = genre
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.update(penulis)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}






