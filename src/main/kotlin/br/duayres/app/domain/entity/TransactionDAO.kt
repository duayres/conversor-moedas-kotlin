package br.duayres.app.domain.entity

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass

class TransactionDAO (id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TransactionDAO>(TransactionTable)
    var userId by TransactionTable.userId
    var originCurrency by TransactionTable.originCurrency
    var originAmount by TransactionTable.originAmount
    var destinationCurrency by TransactionTable.destinationCurrency
    var destinationAmount by TransactionTable.destinationAmount
    var conversionRate by TransactionTable.conversionRate
    var date by TransactionTable.date
    var idVal = id.value;
}