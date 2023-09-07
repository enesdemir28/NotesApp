package com.project.notesapp.activities.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.project.notesapp.activities.model.Note


@Dao
interface DAO {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM Note ORDER BY id Desc")
    fun getAllNotes():LiveData<List<Note>>

    @Delete
    suspend fun deleteNote(note: Note)


}