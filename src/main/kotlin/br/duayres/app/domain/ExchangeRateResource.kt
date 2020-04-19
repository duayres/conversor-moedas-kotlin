package br.duayres.app.domain

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.fasterxml.jackson.module.kotlin.readValue


class ExchangeRateResource {
    companion object {
        fun getExchangeRateByCurrency(currencyFrom: String, currencyTo: String): Double {
            val mapper = ObjectMapper()
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)

            var currencyMap = HashMap<String, Double>();
            val (request, response, result) = "https://api.exchangeratesapi.io/latest?base=${currencyFrom}".httpGet()
                    .responseString()

            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    println("faiou $ex")
                }
                is Result.Success -> {
                    val jsonString: String = result.get()
                    val map = ObjectMapper().readValue<MutableMap<Any, Any>>(jsonString)
                    //val map = mapper.readValue(jsonString, List::class.java).get(0)
                    if (map.containsKey("rates"))
                        currencyMap = map["rates"] as HashMap<String, Double>
                    else if(map.containsKey("error"))
                        error("error ${map.get("error")}")
                    else {
                        error("error retrieving the exchange rate json")
                    }
                }
            }

            return currencyMap.getOrDefault(currencyTo,0.0)
        }
    }
}