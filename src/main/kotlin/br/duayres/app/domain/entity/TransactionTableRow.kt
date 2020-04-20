package br.duayres.app.domain.entity

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.*

class TransactionTableRow (id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TransactionTableRow>(TransactionTable)
    var userId by TransactionTable.userId
    var originCurrency by TransactionTable.originCurrency
    var originAmount by TransactionTable.originAmount
    var destinationCurrency by TransactionTable.destinationCurrency
    var destinationAmount by TransactionTable.destinationAmount
    var conversionRate by TransactionTable.conversionRate
    var date by TransactionTable.date
}