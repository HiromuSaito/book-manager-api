package com.book.manager.bookmanager.application.service

import com.book.manager.bookmanager.domain.model.Book
import com.book.manager.bookmanager.domain.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class AdminBookService(
    val bookRepository: BookRepository
) {

    fun register(book: Book) {
        bookRepository.findWithRental(book.id)?.let { throw IllegalArgumentException("既に存在する書籍ID: ${book.id}") }
        bookRepository.register(book)
    }

    fun update(book: Book) {
        bookRepository.findWithRental(book.id) ?: throw IllegalArgumentException("存在しない書籍ID: ${book.id}")
        bookRepository.save(book)
    }

    fun delete(id: Long) {
        bookRepository.findWithRental(id) ?: throw IllegalArgumentException("存在しない書籍ID: $id")
        bookRepository.delete(id)
    }
}