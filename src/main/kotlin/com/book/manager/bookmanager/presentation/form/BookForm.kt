package com.book.manager.bookmanager.presentation.form

import com.book.manager.bookmanager.domain.model.BookWithRental
import com.book.manager.bookmanager.domain.model.Rental
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import java.time.LocalDateTime


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
    @JsonFormat(pattern = "yyyy/MM/dd")
    val releaseDate: LocalDate,
    val rental: RentalResponse?
) {
    constructor(model: BookWithRental) : this(
        model.book.id,
        model.book.title,
        model.book.author,
        model.book.releaseDate,
        model.rental?.let { RentalResponse(model.rental) }
    )
}

data class RentalResponse(
    val userId: Long,
    @JsonFormat(pattern = "yyyy/MM/dd")
    val rentalDateTime: LocalDateTime,
    @JsonFormat(pattern = "yyyy/MM/dd")
    val returnDeadline: LocalDateTime
) {
    constructor(model: Rental) : this(model.userId, model.rentalDateTime, model.returnDeadline)
}

data class RegisterBookRequest(
    val id: Long,
    val title: String,
    val author: String,
    val releaseDate: LocalDate
)

data class UpdateBookRequest(
    val title: String,
    val author: String,
    val releaseDate: LocalDate
)