package com.fahrulredho0018.assessment02.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "penulis")
data class Penulis(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val judul: String,
    val nama: String,
    val kategori : String,
    val tanggal: String
)
