package com.example.librarykotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.list_item.view.*


class AddBookActivity : AppCompatActivity() {

    fun addOnClick(view: View?) {
        val title = (findViewById<View>(R.id.title) as EditText).text.toString()
        val author = (findViewById<View>(R.id.author) as EditText).text.toString()
        val year = (findViewById<View>(R.id.year) as EditText).text.toString()
        if (title.isNotEmpty() && author.isNotEmpty() && year.isNotEmpty()) {
            val db = baseContext.openOrCreateDatabase("library.db", AppCompatActivity.MODE_PRIVATE, null)
            db.execSQL(String.format("INSERT INTO books VALUES (null, '%s', '%s', %s);", title, author, year))
        }
        val intent = Intent(this@AddBookActivity, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
    }
}