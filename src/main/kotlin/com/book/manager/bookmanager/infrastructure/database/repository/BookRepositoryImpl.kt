package com.book.manager.bookmanager.infrastructure.database.repository

import com.book.manager.bookmanager.domain.model.Book
import com.book.manager.bookmanager.domain.model.BookWithRental
import com.book.manager.bookmanager.domain.repository.BookRepository
import com.book.manager.bookmanager.infrastructure.database.table.BooksEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl : BookRepository {
    override fun findAllWithRental(): List<BookWithRental> {
        return transaction {
            BooksEntity.all().map { toModel(it) }.map { BookWithRental(it, null) }
        }
    }
}

fun toModel(entity: BooksEntity): Book {
    return Book(
        entity.id.value,
        entity.title,
        entity.author,
        entity.releaseDate.toLocalDate()
    )
}
