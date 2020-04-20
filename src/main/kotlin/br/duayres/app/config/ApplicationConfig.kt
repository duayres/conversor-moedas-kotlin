package br.duayres.app.config

import br.duayres.app.domain.Routes
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*

object ApplicationConfig {
    fun start(port: Int = 7000): Javalin = Javalin
            .create()
            .start(port)
            .also { app ->
                Routes.populate(app)
            }
}