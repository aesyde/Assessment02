package com.fahrulredho0018.assessment02.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "penulis")
data class Penulis(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val judul: String,
    val namapenulis: String,
    val tanggal: String
)
