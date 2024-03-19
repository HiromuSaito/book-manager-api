package com.book.manager.bookmanager.presentation.form

import com.book.manager.bookmanager.domain.model.BookWithRental
import com.fasterxml.jackson.annotation.JsonProperty


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

// data class GetBookDetailResponse