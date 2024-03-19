package com.book.manager.bookmanager.infrastructure.database.table

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime


object BooksTable : LongIdTable("books") {
    val title = varchar("title", 128)
    val author = varchar("author", 32)
    val releaseDate = datetime("release_date")
}

class BooksEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<BooksEntity>(BooksTable)

    var title by BooksTable.title
    var author by BooksTable.author
    var releaseDate by BooksTable.releaseDate
}