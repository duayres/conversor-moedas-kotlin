package br.duayres.app.domain

import br.duayres.app.domain.controller.TransactionController
import br.duayres.app.domain.service.TransactionService
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*

object Routes{
    private val transactionController:TransactionController = TransactionController()
    fun populate(app:Javalin): Unit {
        app.routes {
            path("transactions") {
                path(":user-id") {
                    get(transactionController::get)
                }
            }
        }
    }
}