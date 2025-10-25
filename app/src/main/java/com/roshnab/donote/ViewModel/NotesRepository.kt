package com.roshnab.donote.ViewModel

import com.roshnab.donote.Backend.Notes
import com.roshnab.donote.Backend.Notes_Dao

class NotesRepository(private val notesDao: Notes_Dao) {

    fun getAllNotes() = notesDao.GetAllNotes()

    suspend fun addNote(notes: Notes){
        notesDao.AddNote(notes)
    }

    suspend fun deleteNote(id: Int){
        notesDao.DeleteNote(id)
    }

    suspend fun updateNote(title: String, description: String){
        notesDao.UpdateNote(title, description)
    }
}