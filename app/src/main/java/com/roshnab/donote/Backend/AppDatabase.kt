package com.roshnab.donote.Backend

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class, Notes::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun Todo_Dao(): Todo_Dao
    abstract fun Notes_Dao(): Notes_Dao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun GetDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "AppDatabase"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}