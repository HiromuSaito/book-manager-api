package com.book.manager.bookmanager.presentation.controller

import com.book.manager.bookmanager.application.service.BookService
import com.book.manager.bookmanager.presentation.form.Book
import com.book.manager.bookmanager.presentation.form.GetBookListResponse
import com.book.manager.bookmanager.domain.model.BookWithRental
import com.book.manager.bookmanager.presentation.form.GetBookDetailResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController(private val bookService: BookService) {

    @GetMapping("/list")
    fun getList(): GetBookListResponse {
        val books = bookService.getList().map { Book(it) }
        return GetBookListResponse(books)
    }

    @GetMapping("/{id}")
    fun getDetail(@PathVariable id: String): GetBookDetailResponse {
        return GetBookDetailResponse(bookService.getDetail(id.toLong()))
    }
}