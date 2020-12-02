package com.example.librarykotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MyAdapter(context: Context?, library: ArrayList<Book>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    var inflater: LayoutInflater = LayoutInflater.from(context)
    var library: ArrayList<Book> = library

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var author: TextView
        var title: TextView
        var year: TextView
        fun bind(listIndex: Int) {
            author.text = library[listIndex].author
            title.text = library[listIndex].title
            year.text = library[listIndex].year.toString()
        }

        init {
            author = itemView.findViewById(R.id.author)
            title = itemView.findViewById(R.id.title)
            year = itemView.findViewById(R.id.year)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return library.size
    }
}