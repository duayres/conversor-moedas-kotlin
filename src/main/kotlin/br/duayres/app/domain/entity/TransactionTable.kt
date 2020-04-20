package br.duayres.app.domain.entity

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.*

object TransactionTable : Table("transaction"){
    val id = uuid("id").autoIncrement().entityId();//entityId??
    val userId = long("user_id")
    val originCurrency = varchar("origin_currency",3)
    val originAmount = double("origin_amount")
    val destinationCurrency = varchar("origin_currency",3)
    val destinationAmount = double("destination_amount")
    val conversionRate = double("conversion_rate")
    val date = datetime("date")
}