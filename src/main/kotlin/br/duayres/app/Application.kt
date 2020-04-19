package br.duayres.app

import br.duayres.app.config.ApplicationConfig
import br.duayres.app.config.DatabaseManager

fun main(args: Array<String>) {
    DatabaseManager.start()
    ApplicationConfig.start(getHerokuAssignedPort())
        .get("/", { ctx -> ctx.result("Hello World") })
}

fun getHerokuAssignedPort(): Int {
    val herokuPort = System.getenv("PORT")
    return herokuPort?.toInt() ?: 7000
}
