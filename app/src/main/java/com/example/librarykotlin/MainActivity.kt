package com.example.librarykotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null

    fun addBook(view: View?) {
        val intent = Intent(this, AddBookActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.about -> startActivity(Intent(this@MainActivity, AboutActivity::class.java))
            R.id.exit -> {
                finishAffinity()
                exitProcess(0)
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bookList = ArrayList<Book>()
        val db = baseContext.openOrCreateDatabase("library.db", MODE_PRIVATE, null)
        db.execSQL("CREATE TABLE IF NOT EXISTS books (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, author TEXT, year INTEGER)")
        var libSQL = db.rawQuery("Select * from books", null)
        if (libSQL.count == 0) {
            db.execSQL("INSERT INTO books VALUES (null, \"Моби Дик\", \"Герман Мелвилл\", 2005);")
            db.execSQL("INSERT INTO books VALUES (null, \"О Дивный Новый Мир\", \"Олдос Хаксли\", 2016);")
            db.execSQL("INSERT INTO books VALUES (null, \"451 градус по Фаренгейту\", \"Рэй Бредбери\", 2008);")
            db.execSQL("INSERT INTO books VALUES (null, \"Конец детства\", \"Артур Кларк\", 2000);")
            db.execSQL("INSERT INTO books VALUES (null, \"Мы\", \"Евгений Замятин\", 1984);")
        }
        libSQL = db.rawQuery("Select * from books", null)
        libSQL.moveToFirst()
        while (!libSQL.isAfterLast) {
            val title = libSQL.getString(libSQL.getColumnIndex("title"))
            val author = libSQL.getString(libSQL.getColumnIndex("author"))
            val year = libSQL.getString(libSQL.getColumnIndex("year"))
            bookList.add(Book(title, author, year.toInt()))
            libSQL.moveToNext()
        }
        recyclerView = findViewById(R.id.recList)
        val adapter = MyAdapter(this, bookList)
        recyclerView?.addItemDecoration(
            DividerItemDecoration(this,
            DividerItemDecoration.VERTICAL)
        )
        recyclerView?.adapter = adapter
    }
}