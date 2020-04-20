package br.duayres.app.domain.controller

import br.duayres.app.domain.service.TransactionService
import io.javalin.Context

class TransactionController() {
    private val transactionService:TransactionService = TransactionService();
    fun get(ctx:Context){
        ctx.validatedPathParam("user-id")
                .check({ it.isNotBlank() })
                .getOrThrow().also { userId ->
                    transactionService.getTransactionsById(userId.toLong())
                }
    }
}