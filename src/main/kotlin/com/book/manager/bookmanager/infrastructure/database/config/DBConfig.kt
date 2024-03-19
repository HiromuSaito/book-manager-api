package com.book.manager.bookmanager.infrastructure.database.config

import org.jetbrains.exposed.sql.Database
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DBConfig(dataSource: DataSource) {
    init {
        Database.connect(dataSource)
    }
}
