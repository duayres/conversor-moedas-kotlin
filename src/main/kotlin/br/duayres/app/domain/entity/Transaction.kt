package br.duayres.app.domain.entity

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.*
import org.joda.time.DateTime
import org.joda.time.LocalDateTime

data class Transaction(
        var id: Long = 0,
        var userId: Long = 0,
        var originCurrency: String,
        var originAmount: Double,
        var destinationCurrency: String,
        var destinationAmount: Double,
        var conversionRate: Double,
        var date: DateTime){
            constructor() : this(id=0,userId = 0, originAmount = 0.0, originCurrency = "", destinationAmount = 0.0,
                            destinationCurrency = "", date = DateTime.now(), conversionRate = 0.0)
}