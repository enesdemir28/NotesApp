package com.project.notesapp.activities.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.project.notesapp.R
import com.project.notesapp.activities.model.Note
import com.project.notesapp.activities.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {

    lateinit var edtnottitle : EditText
    lateinit var edtnotcontent : EditText
    lateinit var saveupdbtn : Button
    lateinit var viewmodel : NoteViewModel
    var noteID = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        edtnottitle = findViewById(R.id.edt_Not_Title)
        edtnotcontent = findViewById(R.id.edt_note_content)
        saveupdbtn = findViewById(R.id.btn_add_update)
        viewmodel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModel::class.java)


        val nottype = intent.getStringExtra("NoteType")
        if(nottype.equals("Edit")){

            val nottitle = intent.getStringExtra("NoteTitle")
            val notcontent = intent.getStringExtra("NoteContent")
            noteID = intent.getIntExtra("Noteid",-1)
            saveupdbtn.setText("Update")
            edtnottitle.setText(nottitle)
            edtnotcontent.setText(notcontent)
        } else{
            saveupdbtn.setText("Save")
        }

        saveupdbtn.setOnClickListener {

            val NoteTitle = edtnottitle.text.toString()
            val NoteContent = edtnotcontent.text.toString()

            if(nottype.equals("Edit")){
                if(NoteTitle.isNotEmpty() && NoteContent.isNotEmpty()){

                 val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String = sdf.format(Date())
                    val updateNote = Note(NoteTitle,NoteContent,currentDate)
                    updateNote.id = noteID
                    viewmodel.updateNote(updateNote)
                    Toast.makeText(this,"Note Updated",Toast.LENGTH_LONG).show()

                }

            } else{
                if(NoteTitle.isNotEmpty() && NoteContent.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String = sdf.format(Date())
                    viewmodel.addNote(Note(NoteTitle,NoteContent,currentDate))
                    Toast.makeText(this,"Note Added",Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }

}