package com.book.manager.bookmanager.presentation.controller

import com.book.manager.bookmanager.application.service.AuthenticationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val authenticationService: AuthenticationService
) {

    @GetMapping("/{email}")
    fun getUser(@PathVariable email: String) {
        val user = authenticationService.findUser(email)
        println("user is $user")
    }
}