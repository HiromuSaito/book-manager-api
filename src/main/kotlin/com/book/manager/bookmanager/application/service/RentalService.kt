package com.book.manager.bookmanager.application.service

import com.book.manager.bookmanager.domain.model.Rental
import com.book.manager.bookmanager.domain.repository.BookRepository
import com.book.manager.bookmanager.domain.repository.RentalRepository
import com.book.manager.bookmanager.domain.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

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
        val rental = Rental(bookId, userId, now)
        rentalRepository.startRental(rental)
    }

    fun endRental(bookId: Long, userId: Long) {
        userRepository.findById(userId) ?: throw IllegalArgumentException("存在しないUserID:$userId")
        val bookWithRental =
            bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("存在しないbookID:$bookId")

        if (!bookWithRental.isRental) throw IllegalArgumentException("貸出中のbookId:${bookId}")
        if (userId != bookWithRental.rental!!.userId) throw IllegalArgumentException("他のユーザが貸出中の書籍です。bookId:${bookId}")

        rentalRepository.endRental(bookId, userId)
    }
}