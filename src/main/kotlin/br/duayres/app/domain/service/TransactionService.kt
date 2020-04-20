package br.duayres.app.domain.service

import br.duayres.app.domain.ExchangeRateResource
import br.duayres.app.domain.controller.TransactionController
import br.duayres.app.domain.entity.Transaction
import br.duayres.app.domain.entity.TransactionTable
import br.duayres.app.domain.repository.TransactionRepository
import org.joda.time.DateTime

class TransactionService {
    fun getTransactionsById(userId:Long){
        println("recuperando as transações do usuario $userId")
    }

    fun convertAndPersist(currencyFrom:String, currencyTo:String, value:Double):Transaction?{
        val conversionRate:Double = ExchangeRateResource.getExchangeRateByCurrency(currencyFrom, currencyTo);
        return when(conversionRate){
            0.0 -> null
            else -> {
                var transaction = Transaction(conversionRate = 1.0, date = DateTime.now(),
                        originCurrency = currencyFrom, destinationCurrency = currencyTo,
                        originAmount = value.toDouble(), destinationAmount = conversionRate * value)
                //@todo change to bigdecimal
                TransactionRepository.save(transaction);
            }
        }
    }
}