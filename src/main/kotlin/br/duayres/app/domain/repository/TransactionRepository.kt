package br.duayres.app.domain.repository;

import br.duayres.app.domain.entity.Transaction;
import br.duayres.app.domain.entity.TransactionTable;
import br.duayres.app.domain.entity.TransactionTableRow
import br.duayres.app.infra.utils.CopyObjectUtils
import br.duayres.app.infra.utils.CopyObjectUtils.copyPropsFrom
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object TransactionRepository {
    fun save(transaction:Transaction):Transaction{
        val newId = transaction {
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
        }

        var savedTransaction = transaction;
        //savedTransaction.id = newId as Long;
        return savedTransaction
    }

    fun listTransactionsByUserId(userId:Long):List<Transaction>{
        val transactions = ArrayList<Transaction>()
        transaction {
            val transactionRows = TransactionTableRow.find { TransactionTable.userId eq userId }.toList();

            for (transaction in transactionRows) {
                var copyTo = Transaction()
                copyTo.copyPropsFrom(transaction)
                transactions.add(copyTo);
            }
        }
        return transactions;
    }
}
