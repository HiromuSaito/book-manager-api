package com.book.manager.bookmanager.infrastructure.database.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime


object BooksTable : LongIdTable("books") {
    val title = varchar("title", 128)
    val author = varchar("author", 32)
    val releaseDate = datetime("release_date")
}