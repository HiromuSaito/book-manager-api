package com.book.manager.bookmanager.infrastructure.database.table

import com.book.manager.bookmanager.domain.enum.RoleType
import org.jetbrains.exposed.dao.id.LongIdTable


object UsersTable : LongIdTable("books") {
    val email = varchar("email", 256)
    val password = varchar("password", 128)
    val name = varchar("name", 32)
    val roleType = enumerationByName("role_type", 10, RoleType::class)
}