package com.book.manager.bookmanager.presentation.controller

import com.book.manager.bookmanager.application.service.AdminBookService
import com.book.manager.bookmanager.domain.model.Book
import com.book.manager.bookmanager.presentation.form.RegisterBookRequest
import com.book.manager.bookmanager.presentation.form.UpdateBookRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/books")
class AdminBookController(
    val adminBookService: AdminBookService
) {

    @PostMapping
    fun register(@RequestBody request: RegisterBookRequest) {
        adminBookService.register(
            Book(
                request.id,
                request.title,
                request.author,
                request.releaseDate
            )
        )
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody request: UpdateBookRequest) {
        adminBookService.update(
            Book(
                id.toLong(),
                request.title,
                request.author,
                request.releaseDate
            )
        )
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) {
        adminBookService.delete(id.toLong())
    }

}