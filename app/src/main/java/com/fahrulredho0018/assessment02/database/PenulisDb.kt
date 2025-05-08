package com.fahrulredho0018.assessment02.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fahrulredho0018.assessment02.model.Penulis

@Database(entities = [Penulis::class], version = 2, exportSchema = false)
abstract class PenulisDb : RoomDatabase() {

    abstract val dao: PenulisDao

    companion object {

        @Volatile
        private var INSTANCE: PenulisDb? = null

        // Migrasi dari versi 1 ke versi 2: Tambahkan kolom isDeleted
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE event ADD COLUMN isDeleted INTEGER NOT NULL DEFAULT 0"
                )
            }
        }

        fun getInstance(context: Context): PenulisDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PenulisDb::class.java,
                        "event.db"
                    )
                        .addMigrations(MIGRATION_1_2) // Tambahkan migrasi di sini
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}
