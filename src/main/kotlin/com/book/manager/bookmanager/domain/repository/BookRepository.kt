package com.book.manager.bookmanager.domain.repository

import com.book.manager.bookmanager.domain.model.BookWithRental

interface BookRepository {
    fun findAllWithRental(): List<BookWithRental>
}