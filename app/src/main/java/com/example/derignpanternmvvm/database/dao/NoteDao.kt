package com.example.derignpanternmvvm.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.derignpanternmvvm.model.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(model: Note) // suspend thuộc counrotin

    @Update
    suspend fun update(model: Note)

    @Delete
    suspend fun delete(model: Note)

    @Query("SELECT * FROM note_table")
    fun getAllData(): LiveData<List<Note>>
}