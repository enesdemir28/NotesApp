package com.project.notesapp.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.notesapp.R
import com.project.notesapp.activities.model.Note

class NoteRvAdapter(
    val context : Context,
    val noteClickInterface: NoteClickInterface,
    val noteClickDeleteInterface: NoteClickDeleteInterface) : RecyclerView.Adapter<NoteRvAdapter.ViewHolder>(){

        private val allnotes = ArrayList<Note>()
        inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

            val nottitlerv = itemView.findViewById<TextView>(R.id.note_title)
            val deleterv = itemView.findViewById<ImageView>(R.id.dlticon)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_rv_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.nottitlerv.setText(allnotes.get(position).title)

        holder.nottitlerv.setOnClickListener {
            noteClickInterface.onNoteclick(allnotes.get(position))
        }

            holder.deleterv.setOnClickListener{
          noteClickDeleteInterface.ondeleteiconclick(allnotes.get(position))
        }
    }

    override fun getItemCount(): Int {

        return allnotes.size
    }
    fun updatelist(newlist : List<Note>){
       allnotes.clear()
        allnotes.addAll(newlist)
        notifyDataSetChanged()

    }
}


interface NoteClickDeleteInterface{

    fun ondeleteiconclick(note: Note)
}

interface NoteClickInterface{

    fun onNoteclick(note: Note)
}