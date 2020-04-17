package br.duayres.app.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.util.*

object DataSourceProvider {
    val datasource = HikariDataSource(HikariConfig(properties()))

    // init {
    //     HikariConfig().let { config ->
    //         config.jdbcUrl = jdbcUrl
    //         config.username = username
    //         config.password = password
    //         HikariDataSource(config)
    //     }
    // }
    
    private fun properties() = Properties().apply {
        put("jdbcUrl", "jdbc:h2:mem:test")//@todo System.getenv("MYSQL_URL")
        put("dataSource.user", "root")
        put("dataSource.password", "")
    }
}