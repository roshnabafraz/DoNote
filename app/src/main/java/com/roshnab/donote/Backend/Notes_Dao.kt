package com.roshnab.donote.Backend

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract interface Notes_Dao {

    @Query("SELECT * FROM Notes")
    fun GetAllNotes(): Flow<List<Notes>>

    @Insert
    suspend fun AddNote(notes: Notes)

    @Query("DELETE FROM Notes WHERE id = :id")
    suspend fun DeleteNote(id: Int)

    @Query("UPDATE Notes SET title = :title, description = :description")
    suspend fun UpdateNote(title: String, description: String)
}