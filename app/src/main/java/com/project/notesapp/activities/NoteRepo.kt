package com.project.notesapp.activities

import androidx.lifecycle.LiveData

class NoteRepo(private val notesdao: DAO) {


    val allNotes : LiveData<List<Note>> = notesdao.getAllNotes()

    suspend fun addNote(note: Note) = notesdao.addNote(note)

    suspend fun updNote(note: Note) = notesdao.updateNote(note)

    suspend fun delNote(note: Note) = notesdao.deleteNote(note)




}