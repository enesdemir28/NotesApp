package com.project.notesapp.activities

import androidx.lifecycle.LiveData
import androidx.room.*


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