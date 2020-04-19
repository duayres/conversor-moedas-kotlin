package br.duayres.app

import br.duayres.app.config.*

fun main(args: Array<String>) {
    DatabaseManager.start()
    ApplicationConfig.start(getHerokuAssignedPort())
}

fun getHerokuAssignedPort(): Int {
    val herokuPort = System.getenv("PORT")
    return herokuPort?.toInt() ?: 7000
}
