package br.duayres.app.domain.repository;

import br.duayres.app.domain.entity.Transaction;
import br.duayres.app.domain.entity.TransactionTable;
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object TransactionRepository {
    fun save(transaction:Transaction):Transaction{
        transaction {
            TransactionTable.insert {
                //it[id] = transaction.
                it[userId] = 1L;//@fixme when auth is in place
                it[originCurrency] = transaction.originCurrency
                it[originAmount] = transaction.originAmount
                it[destinationCurrency] = transaction.destinationCurrency
                it[destinationAmount] = transaction.destinationAmount;
                it[conversionRate] = transaction.conversionRate
                it[date] = transaction.date
            }
        }.generatedKey.also {
            var savedTransaction = transaction;
            savedTransaction.id = it as Long;
            return savedTransaction
        }
    }
}
