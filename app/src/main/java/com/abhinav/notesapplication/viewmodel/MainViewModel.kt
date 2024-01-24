package com.abhinav.notesapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhinav.notesapplication.model.Note
import com.abhinav.notesapplication.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun insert(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(note)
        }
    }

    fun update(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(note)
        }
    }

    fun delete(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(note)
        }
    }

    fun getNotes() : LiveData<List<Note>> {
        return repository.getNotes()
    }
}