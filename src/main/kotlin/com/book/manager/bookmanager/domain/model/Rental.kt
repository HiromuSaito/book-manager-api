package com.book.manager.bookmanager.domain.model

import java.time.LocalDateTime

private const val RENTAL_TERM_DAYS: Long = 14

data class Rental(
    val bookId: Long,
    val userId: Long,
    val rentalDateTime: LocalDateTime,
    val returnDeadline: LocalDateTime
) {
    constructor (bookId: Long, userId: Long, rentalDateTime: LocalDateTime) : this(
        bookId,
        userId,
        rentalDateTime,
        rentalDateTime.plusDays(RENTAL_TERM_DAYS)
    )
}