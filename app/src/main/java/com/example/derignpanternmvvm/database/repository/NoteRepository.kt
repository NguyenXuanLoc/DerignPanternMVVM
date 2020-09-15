package com.example.derignpanternmvvm.database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.derignpanternmvvm.database.NoteDatabase
import com.example.derignpanternmvvm.database.dao.NoteDao
import com.example.derignpanternmvvm.model.Note

// Cung cấp lựa chọn thích hợp load data local or data server
class NoteRepository(app: Application) {
    private val noteDao: NoteDao

    init {
        val noteDatabase: NoteDatabase = NoteDatabase.getInstance(app)
        noteDao = noteDatabase.noteDao()
    }

    suspend fun insertNote(note: Note) = noteDao.insert(note)
    suspend fun update(note: Note) = noteDao.update(note)
    suspend fun delete(note: Note) = noteDao.delete(note)
    fun getAllNote(): LiveData<List<Note>> = noteDao.getAllData()
}