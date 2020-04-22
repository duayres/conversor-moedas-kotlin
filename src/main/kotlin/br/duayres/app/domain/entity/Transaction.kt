package br.duayres.app.domain.entity

import com.fasterxml.jackson.annotation.JsonFormat
import org.joda.time.DateTime

data class Transaction(
        var id: Long = 0,
        var userId: Long = 0,
        var originCurrency: String,
        var originAmount: Double,
        var destinationCurrency: String,
        var destinationAmount: Double,
        var conversionRate: Double,
        @get:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm")
        var date: DateTime){
            constructor() : this(id=0,userId = 0, originAmount = 0.0, originCurrency = "", destinationAmount = 0.0,
                            destinationCurrency = "", date = DateTime.now(), conversionRate = 0.0)
}