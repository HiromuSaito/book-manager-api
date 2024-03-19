package com.book.manager.bookmanager.application.service

import com.book.manager.bookmanager.domain.model.Rental
import com.book.manager.bookmanager.domain.repository.BookRepository
import com.book.manager.bookmanager.domain.repository.RentalRepository
import com.book.manager.bookmanager.domain.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

private const val RENTAL_TERM_DAYS: Long = 14

@Service
class RentalService(
    private val userRepository: UserRepository,
    private val bookRepository: BookRepository,
    private val rentalRepository: RentalRepository
) {

    fun startRental(bookId: Long, userId: Long) {
        userRepository.findById(userId) ?: throw IllegalArgumentException("存在しないUserID:$userId")
        val bookWithRental =
            bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("存在しないbookID:$bookId")
        if (bookWithRental.isRental) throw IllegalArgumentException("貸出中のbookId:${bookId}")

        val now = LocalDateTime.now()
        val returnDate = now.plusDays(RENTAL_TERM_DAYS)
        val rental = Rental(bookId, userId, now, returnDate)
        rentalRepository.startRental(rental)
    }
}