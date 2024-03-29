package com.book.manager.bookmanager.domain.model

import com.book.manager.bookmanager.domain.enum.RoleType

data class User(
    val id: Long,
    val email: String,
    val password: String,
    val name: String,
    val roleType: RoleType
) {
}
