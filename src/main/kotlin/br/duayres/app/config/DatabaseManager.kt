package br.duayres.app.config

import br.duayres.app.domain.entity.TransactionTable
import org.jetbrains.exposed.sql.Database

object DatabaseManager {
    fun start(){
        Database.connect(DataSourceProvider.datasource)
        SchemaCreation.createTables(TransactionTable)
    }
}