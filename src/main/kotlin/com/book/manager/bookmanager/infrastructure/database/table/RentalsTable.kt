package com.book.manager.bookmanager.infrastructure.database.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime as datetime1


object RentalsTable : Table("rentals") {
    val bookId = long("book_id")
    val userId = long("user_id")
    val rentalDateTime = datetime1("rental_datetime")
    val deadLine = datetime1("return_deadline")
}