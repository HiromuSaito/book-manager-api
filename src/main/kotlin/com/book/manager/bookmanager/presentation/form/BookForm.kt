package com.book.manager.bookmanager.presentation.form

import com.book.manager.bookmanager.domain.model.BookWithRental
import com.book.manager.bookmanager.domain.model.Rental
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate


data class GetBookListResponse(val bookList: List<Book>)

data class Book(
    val id: Long,
    val title: String,
    val author: String,
    @JsonProperty("isRental")
    val isRental: Boolean
) {
    constructor(model: BookWithRental) : this(model.book.id, model.book.title, model.book.author, model.isRental)
}

data class GetBookDetailResponse(
    val id: Long,
    val title: String,
    val author: String,
    val releaseDate: LocalDate,
    val rental: Rental?
) {
    constructor(model: BookWithRental) : this(
        model.book.id,
        model.book.title,
        model.book.author,
        model.book.releaseDate,
        model.rental
    )
}