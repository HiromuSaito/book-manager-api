package com.book.manager.bookmanager.infrastructure.database.repository

import com.book.manager.bookmanager.domain.model.Book
import com.book.manager.bookmanager.domain.model.BookWithRental
import com.book.manager.bookmanager.domain.model.Rental
import com.book.manager.bookmanager.domain.repository.BookRepository
import com.book.manager.bookmanager.infrastructure.database.table.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl : BookRepository {
    override fun findAllWithRental(): List<BookWithRental> {
        return transaction {
            addLogger(StdOutSqlLogger)
            BooksTable.join(
                RentalsTable,
                JoinType.LEFT,
                onColumn = BooksTable.id,
                otherColumn = RentalsTable.bookId
            )
                .selectAll().map { toModel(it) }
                .toList()
        }
    }

    override fun findWithRental(id: Long): BookWithRental? {
        return transaction {
            addLogger(StdOutSqlLogger)
            BooksTable.join(
                RentalsTable,
                JoinType.LEFT,
                onColumn = BooksTable.id,
                otherColumn = RentalsTable.bookId
            )
                .select { BooksTable.id eq id }
                .map { toModel(it) }
                .singleOrNull()
        } 
    }
}

fun toModel(result: ResultRow): BookWithRental {
    val book = Book(
        result[BooksTable.id].value,
        result[BooksTable.title],
        result[BooksTable.author],
        result[BooksTable.releaseDate].toLocalDate(),
    )
    val rental = result.getOrNull(RentalsTable.bookId)?.let {
        Rental(
            result[RentalsTable.bookId],
            result[RentalsTable.userId],
            result[RentalsTable.rentalDateTime],
            result[RentalsTable.deadLine]
        )
    }
    return BookWithRental(book, rental)
}
