package com.project.notesapp.activities.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.project.notesapp.R
import com.project.notesapp.activities.model.Note
import com.project.notesapp.activities.viewmodel.NoteViewModel
import com.project.notesapp.recyclerview.NoteClickDeleteInterface
import com.project.notesapp.recyclerview.NoteClickInterface
import com.project.notesapp.recyclerview.NoteRvAdapter


class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {


    lateinit var noteRv: RecyclerView
    lateinit var fltbtn: FloatingActionButton
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteRv = findViewById(R.id.rvnotes)
        fltbtn = findViewById(R.id.fltbtn)
        noteRv.layoutManager = GridLayoutManager(this,2)

        val noteRvAdapter = NoteRvAdapter(this,this,this)
        noteRv.adapter = noteRvAdapter
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer { list->
            list?.let {
                noteRvAdapter.updatelist(it)
            }


        })
        fltbtn.setOnClickListener{
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }

    override fun onNoteclick(note: Note) {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("NoteType","Edit")
        intent.putExtra("NoteTitle",note.title)
        intent.putExtra("NoteContent",note.content)
        intent.putExtra("Noteid",note.id)
        startActivity(intent)
        this.finish()
    }

    override fun ondeleteiconclick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.title} Deleted",Toast.LENGTH_LONG).show()
    }
}