package br.duayres.app.domain.controller

import br.duayres.app.domain.service.TransactionService
import io.javalin.Context
import org.koin.ext.isFloat

class TransactionController() {
    private val transactionService:TransactionService = TransactionService();
    fun get(ctx:Context){
        ctx.validatedPathParam("user-id")
                .check({ it.isNotBlank() })
                .getOrThrow().also { userId ->
                    transactionService.getTransactionsById(userId.toLong())
                            .let { ctx.json(it) }
                }
    }

    fun convertAndPersist(ctx:Context){
        val regexPattern = "[a-zA-Z]{3}";
        var fromCurrency: String = ctx.validatedPathParam("origin")
                .check({ it.isNotBlank() })
                .matches(regexPattern)
                .getOrThrow()
        var toCurrency: String = ctx.validatedPathParam("destination")
                .check({ it.isNotBlank() })
                .matches(regexPattern)
                .getOrThrow()

        var value: String = ctx.validatedQueryParam("value")//ctx.validatedFormParam("value")
                .check({ it.isNotBlank() })
                .check({ it.isFloat() })
                .getOrThrow()

        transactionService.convertAndPersist(fromCurrency, toCurrency, value.toDouble(),ctx)?.let { ctx.json(it) }
    }
}