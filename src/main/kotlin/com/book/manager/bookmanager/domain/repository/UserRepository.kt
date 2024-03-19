package com.book.manager.bookmanager.domain.repository

import com.book.manager.bookmanager.domain.model.User

interface UserRepository {
    fun findByEmail(email: String): User?

    fun findById(id: Long): User?
}