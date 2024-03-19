package com.book.manager.bookmanager.infrastructure.database.repository

import com.book.manager.bookmanager.domain.model.User
import com.book.manager.bookmanager.domain.repository.UserRepository
import com.book.manager.bookmanager.infrastructure.database.table.UsersTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : UserRepository {

    override fun find(email: String): User? {
        return UsersTable.select {
            UsersTable.email eq email
        }.map { toModel(it) }.singleOrNull()
    }

    private fun toModel(result: ResultRow): User {
        return User(
            result[UsersTable.id].value,
            result[UsersTable.email],
            result[UsersTable.password],
            result[UsersTable.name],
            result[UsersTable.roleType]
        )
    }

}