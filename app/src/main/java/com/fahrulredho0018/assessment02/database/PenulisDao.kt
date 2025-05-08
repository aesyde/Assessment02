package com.fahrulredho0018.assessment02.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fahrulredho0018.assessment02.model.Penulis
import kotlinx.coroutines.flow.Flow

//@Dao
//interface PenulisDao {
//    @Query("SELECT * FROM penulis ORDER BY id DESC")
//    fun getPenulis(): Flow<List<Penulis>>
//
//    @Query("SELECT * FROM penulis WHERE id = :id")
//    suspend fun getPenulisById(id: Long): Penulis?
//
//    @Insert
//    suspend fun insert(penulis: Penulis)
//
//    @Update
//    suspend fun update(penulis: Penulis)
//
//    @Query("DELETE FROM penulis WHERE id = :id")
//    suspend fun deleteById(id: Long)
//}

@Dao
interface PenulisDao {
    @Insert suspend fun insert(penulis: Penulis)
    @Update suspend fun update(penulis: Penulis)

    @Query("SELECT * FROM penulis WHERE isDeleted = 0 ORDER BY id ASC")
    fun getPenulis(): Flow<List<Penulis>>

    @Query("SELECT * FROM penulis WHERE isDeleted = 1 ORDER BY id ASC")
    fun getDeletedPenulis(): Flow<List<Penulis>>

    @Query("SELECT * FROM penulis WHERE id = :id")
    suspend fun getPenulisById(id: Long): Penulis?

    @Query("DELETE FROM penulis WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("UPDATE penulis SET isDeleted = 1 WHERE id = :id")
    suspend fun softDelete(id: Long)

    @Query("UPDATE penulis SET isDeleted = 0 WHERE id = :id")
    suspend fun restore(id: Long)
}
