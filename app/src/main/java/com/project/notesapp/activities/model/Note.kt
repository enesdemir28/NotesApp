package com.project.notesapp.activities.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(

   @ColumnInfo(name = "title") val title: String,
   @ColumnInfo(name = "content") val content: String,
   @ColumnInfo(name = "date") val date: String

) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}