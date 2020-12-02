package com.example.librarykotlin

import java.util.*

class Book(var title: String, var author: String, var year: Int) {

    override fun toString(): String {
        return """
              title: $title
              author: $author
              year: $year
              """.trimIndent()
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Book) return false
        val book = o
        return year == book.year && title == book.title && author == book.author
    }

    override fun hashCode(): Int {
        return Objects.hash(title, author, year)
    }
}