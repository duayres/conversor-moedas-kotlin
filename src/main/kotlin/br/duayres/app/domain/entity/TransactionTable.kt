package br.duayres.app.domain.entity

import com.fasterxml.jackson.annotation.JsonFormat
import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.*

object TransactionTable : LongIdTable("transaction"){
    val userId = long("user_id")
    val originCurrency = varchar("origin_currency",3)
    val originAmount = double("origin_amount")
    val destinationCurrency = varchar("destination_currency",3)
    val destinationAmount = double("destination_amount")
    val conversionRate = double("conversion_rate")
    @get:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm")
    val date = datetime("date")
}