package com.fahrulredho0018.assessment02.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fahrulredho0018.assessment02.model.Penulis
import kotlinx.coroutines.flow.Flow

@Dao
interface PenulisDao {

    @Insert
    suspend fun insert(penulis: Penulis)

    @Update
    suspend fun update(penulis: Penulis)

    @Query("SELECT * FROM penulis ORDER BY tanggal DESC")
    fun getPenulis() : Flow<List<Penulis>>
}