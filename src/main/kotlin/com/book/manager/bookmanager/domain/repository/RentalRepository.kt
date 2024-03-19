package com.book.manager.bookmanager.domain.repository

import com.book.manager.bookmanager.domain.model.Rental


interface RentalRepository {
    fun startRental(rental: Rental)
}