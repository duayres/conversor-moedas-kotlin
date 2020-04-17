package br.duayres.app.config

import org.jetbrains.exposed.sql.Database

object DatabaseManager {
    fun start(){
        Database.connect(DataSourceProvider.datasource)
        //DatabaseComposer.createTables(Transaction)//centered-ddl
    }
}