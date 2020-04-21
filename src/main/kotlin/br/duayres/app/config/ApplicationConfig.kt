package br.duayres.app.config

import br.duayres.app.domain.Routes
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.json.JavalinJackson
import java.text.SimpleDateFormat

object ApplicationConfig {
    fun start(port: Int = 7000): Javalin = Javalin
            .create()
            .start(port)
            .also { app ->
                Routes.populate(app)
                configureMapper()
            }

    private fun configureMapper() {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss.SSS'Z'")
        JavalinJackson.configure(
                jacksonObjectMapper()
                        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                        .setDateFormat(dateFormat)
                        //.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true)
        )
    }
}