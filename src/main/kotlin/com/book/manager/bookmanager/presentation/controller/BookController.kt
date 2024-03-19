package com.book.manager.bookmanager.presentation.controller

import com.book.manager.bookmanager.application.service.BookService
import com.book.manager.bookmanager.presentation.form.Book
import com.book.manager.bookmanager.presentation.form.BookList
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("books")
class BookController(private val bookService: BookService) {

    @GetMapping("list")
    fun getList(): BookList {
        val list = bookService.getList().map { b -> Book(b) }
        return BookList(list)
    }
}