package br.duayres.app.config

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*

object ApplicationConfig {
    fun start(port:Int = 7000): Javalin = Javalin
        .create()
        .start(port)
        //@TODO .routes {} //in a separated file maybe?!?
}