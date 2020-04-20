package br.duayres.app.domain.entity

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.*
import org.joda.time.DateTime
import org.joda.time.LocalDateTime

data class Transaction(
        var id: Long = 0,
        val userId: Long = 0,
        val originCurrency: String,
        val originAmount: Double,
        val destinationCurrency: String,
        val destinationAmount: Double,
        val conversionRate: Double,
        val date: DateTime)