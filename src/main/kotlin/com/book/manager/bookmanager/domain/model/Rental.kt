package com.book.manager.bookmanager.domain.model

import java.time.LocalDateTime

data class Rental(
    val bookId: Long,
    val userId: String,
    val rentalDateTime: LocalDateTime,
    val returnDeadline: LocalDateTime
) {
}