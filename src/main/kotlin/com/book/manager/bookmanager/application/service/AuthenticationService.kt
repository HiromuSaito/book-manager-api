package com.book.manager.bookmanager.application.service

import com.book.manager.bookmanager.domain.model.User
import com.book.manager.bookmanager.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    val userRepository: UserRepository
) {

    fun findUser(email: String): User? {
        return userRepository.findByEmail(email)
    }
}