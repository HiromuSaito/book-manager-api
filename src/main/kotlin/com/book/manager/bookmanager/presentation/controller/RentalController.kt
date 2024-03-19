package com.book.manager.bookmanager.presentation.controller

import com.book.manager.bookmanager.application.service.RentalService
import com.book.manager.bookmanager.application.service.security.BookManagerUserDetails
import com.book.manager.bookmanager.presentation.form.RentalStartRequest
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rentals")
class RentalController(
    private val rentalService: RentalService
) {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, value = HttpStatus.CREATED)
    fun startRental(
        @AuthenticationPrincipal user: BookManagerUserDetails,
        @RequestBody body: RentalStartRequest
    ) {
        rentalService.startRental(body.bookId, user.id)
    }

    @DeleteMapping("{bookId}")
    fun endRental(
        @AuthenticationPrincipal user: BookManagerUserDetails,
        @PathVariable bookId: String,
    ) {
        rentalService.endRental(bookId.toLong(), user.id)
    }
}