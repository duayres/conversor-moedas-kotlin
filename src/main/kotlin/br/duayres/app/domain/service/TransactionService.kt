package br.duayres.app.domain.service

import br.duayres.app.application.ConversionException
import br.duayres.app.application.ConversionResult
import br.duayres.app.domain.ExchangeRateResource
import br.duayres.app.domain.entity.Transaction
import br.duayres.app.domain.repository.TransactionRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.javalin.Context
import org.joda.time.DateTime
import java.text.SimpleDateFormat


class TransactionService {
    fun getTransactionsById(userId:Long):List<Transaction>{
        return TransactionRepository.listTransactionsByUserId(userId)
    }

    fun convertAndPersist(currencyFrom: String, currencyTo: String, value: Double, ctx: Context):Transaction?{
        when(val conversionRateResult: ConversionResult = ExchangeRateResource.getExchangeRateByCurrency(currencyFrom, currencyTo)){
            is ConversionResult.Fail -> ctx.status(400).json(ConversionException(conversionRateResult.value))
            is ConversionResult.Success -> {
                val conversionRate = conversionRateResult.value
                var transaction = Transaction(conversionRate = conversionRate, date = DateTime.now(),
                        originCurrency = currencyFrom, destinationCurrency = currencyTo,
                        originAmount = value.toDouble(), destinationAmount = conversionRate * value)
                println(jacksonObjectMapper().writeValueAsString(transaction))
                //@todo change to bigdecimal
                return TransactionRepository.save(transaction);
            }
        }
        return null;
    }
}