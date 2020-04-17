package br.duayres.app

import br.duayres.app.config.*

fun main(args: Array<String>) {
    DatabaseManager.start()
    ApplicationConfig.start()
}
