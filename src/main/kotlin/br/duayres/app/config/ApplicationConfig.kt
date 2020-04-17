package br.duayres.app.config

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*

object ApplicationConfig {
    fun start(): Javalin = Javalin
        .create()
        .start(7000)
        //@TODO .routes {} //in a separated file maybe?!?
}