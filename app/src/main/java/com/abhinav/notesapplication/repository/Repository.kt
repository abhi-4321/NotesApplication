package com.abhinav.notesapplication.repository

import androidx.lifecycle.LiveData
import com.abhinav.notesapplication.database.Dao
import com.abhinav.notesapplication.database.NotesDatabase
import com.abhinav.notesapplication.model.Note
import javax.inject.Inject

class Repository @Inject constructor(private val database: NotesDatabase) {

    suspend fun insert(note : Note){
        database.dao().insert(note)
    }

    suspend fun update(note : Note){
        database.dao().update(note)
    }

    suspend fun delete(note : Note){
        database.dao().delete(note)
    }

    fun getNotes() : LiveData<List<Note>> {
        return database.dao().getNotes()
    }

}