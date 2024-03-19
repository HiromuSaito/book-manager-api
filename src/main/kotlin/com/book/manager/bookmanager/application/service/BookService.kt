package com.book.manager.bookmanager.application.service

import com.book.manager.bookmanager.domain.model.BookWithRental
import com.book.manager.bookmanager.domain.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {

    fun getList(): List<BookWithRental> {
        return bookRepository.findAllWithRental()
    }

    fun getDetail(id: Long): BookWithRental {
        return bookRepository.findWithRental(id) ?:throw IllegalArgumentException()
    }
}

