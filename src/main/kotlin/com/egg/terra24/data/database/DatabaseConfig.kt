package com.egg.terra24.data.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DatabaseConfig {
    @Value("\${spring.datasource.url}")
    private val dbUrl: String? = null

    @Value("\${spring.datasource.password}")
    private val dbPassword: String? = null

    @Value("\${spring.datasource.username}")
    private val dbUserName: String? = null

    @Bean
    fun dataSource(): DataSource {
        val config = HikariConfig()
        config.jdbcUrl = dbUrl
        config.password = dbPassword
        config.username = dbUserName
        return HikariDataSource(config)
    }
}