package com.abhinav.notesapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    val createdAt : String,
    val updatedAt : String,
    val title : String,
    val body : String,
    @PrimaryKey(autoGenerate = true) val id : Int
)
