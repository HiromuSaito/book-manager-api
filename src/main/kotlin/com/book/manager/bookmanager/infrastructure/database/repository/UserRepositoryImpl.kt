package com.book.manager.bookmanager.infrastructure.database.repository

import com.book.manager.bookmanager.domain.model.User
import com.book.manager.bookmanager.domain.repository.UserRepository
import com.book.manager.bookmanager.infrastructure.database.table.UsersTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : UserRepository {

    override fun findByEmail(email: String): User? {
        return transaction {
            addLogger(StdOutSqlLogger)
            UsersTable.select {
                UsersTable.email eq email
            }.map { toModel(it) }.singleOrNull()
        }
    }

    override fun findById(id: Long): User? {
        return transaction {
            addLogger(StdOutSqlLogger)
            UsersTable.select {
                UsersTable.id eq id
            }.map { toModel(it) }.singleOrNull()
        }
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