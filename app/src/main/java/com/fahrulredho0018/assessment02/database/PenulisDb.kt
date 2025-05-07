package com.fahrulredho0018.assessment02.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fahrulredho0018.assessment02.model.Penulis

@Database(entities = [Penulis::class], version = 2)
abstract class PenulisDb : RoomDatabase() {
    abstract val dao: PenulisDao
    companion object {
        @Volatile
        private var INSTANCE: PenulisDb? = null
        fun getInstance(context: Context): PenulisDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PenulisDb::class.java,
                    "penulis_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
