package com.book.manager.bookmanager.infrastructure.database.repository

import com.book.manager.bookmanager.domain.model.Rental
import com.book.manager.bookmanager.domain.repository.RentalRepository
import com.book.manager.bookmanager.infrastructure.database.table.RentalsTable
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class RentalRepositoryImpl : RentalRepository {
    override fun startRental(rental: Rental) {
        transaction {
            addLogger(StdOutSqlLogger)
            RentalsTable.insert {
                it[bookId] = rental.bookId
                it[userId] = rental.userId
                it[rentalDateTime] = rental.rentalDateTime
                it[returnDeadline] = rental.returnDeadline
            }
        }
    }
}