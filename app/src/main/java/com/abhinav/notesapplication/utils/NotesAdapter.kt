package com.abhinav.notesapplication.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.abhinav.notesapplication.R
import com.abhinav.notesapplication.model.Note
import javax.inject.Inject

class NotesAdapter @Inject constructor() : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var arrayList : List<Note> = emptyList()

    fun initList(list: List<Note>) {
        arrayList = list
    }

    inner class NotesViewHolder(item : View) : ViewHolder(item){
        val time : TextView = item.findViewById(R.id.time)
        val title : TextView = item.findViewById(R.id.title)
        val body : TextView = item.findViewById(R.id.body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.time.text = arrayList[position].updatedAt
        holder.title.text = arrayList[position].title
        holder.body.text = arrayList[position].body
    }
}