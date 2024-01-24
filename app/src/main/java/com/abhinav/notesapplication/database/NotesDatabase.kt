package com.abhinav.notesapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abhinav.notesapplication.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun dao() : Dao
}