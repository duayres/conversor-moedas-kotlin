package br.duayres.app.domain

import br.duayres.app.application.web.controller.TransactionController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*

object Routes{
    private val transactionController:TransactionController = TransactionController()
    fun populate(app:Javalin): Unit {
        app.routes {
            path("transaction") {
                path(":user-id") {
                    get(transactionController::get)
                }
            }
            path("convert/:origin/:destination"){
                get(transactionController::convertAndPersist)
            }
        }
    }
}