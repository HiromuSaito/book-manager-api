package com.book.manager.bookmanager

import org.jetbrains.exposed.sql.Database
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookManagerApplication

fun main(args: Array<String>) {
    Database.connect(
        "jdbc:mysql://127.0.0.1:3306/db",
        driver = "com.mysql.jdbc.Driver",
        user = "admin",
        password = "password"
    )
    runApplication<BookManagerApplication>(*args)
}
