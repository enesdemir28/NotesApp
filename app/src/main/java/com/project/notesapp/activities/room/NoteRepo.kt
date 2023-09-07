package com.project.notesapp.activities.room

import androidx.lifecycle.LiveData
import com.project.notesapp.activities.model.Note
import com.project.notesapp.activities.room.DAO

class NoteRepo(private val notesdao: DAO) {


    val allNotes : LiveData<List<Note>> = notesdao.getAllNotes()

    suspend fun addNote(note: Note) = notesdao.addNote(note)

    suspend fun updNote(note: Note) = notesdao.updateNote(note)

    suspend fun delNote(note: Note) = notesdao.deleteNote(note)




}