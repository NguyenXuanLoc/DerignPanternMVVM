package com.example.derignpanternmvvm.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.derignpanternmvvm.database.repository.NoteRepository
import com.example.derignpanternmvvm.model.Note
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : ViewModel() {
    private val noteRepository: NoteRepository = NoteRepository(application)
    fun insertNote(note: Note) = viewModelScope.launch {
        noteRepository.insertNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.update(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.delete(note)
    }

    fun getAllNote(): LiveData<List<Note>> = noteRepository.getAllNote()
    class NoteViewModelFactory(var application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            // so sánh xem modelClass cso phai la the hien cua NoteViewModel
            if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
                return NoteViewModel(application) as T
            }
            throw IllegalAccessException("Unable construct...")
        }
    }
}