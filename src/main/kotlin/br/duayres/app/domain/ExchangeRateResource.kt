package br.duayres.app.domain

import br.duayres.app.application.ConversionException
import br.duayres.app.application.ConversionResult
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.fasterxml.jackson.module.kotlin.readValue
import kotlin.String as String


class ExchangeRateResource {
    companion object {
        fun getExchangeRateByCurrency(currencyFrom: String, currencyTo: String): ConversionResult {
            val mapper = jacksonObjectMapper()

            var currencyMap = HashMap<String, Double>();
            val (request, response, result) = "https://api.exchangeratesapi.io/latest?base=${currencyFrom}".httpGet()
                    .responseString()

            when (result) {
                is Result.Failure -> {
                    val message = mapper.readValue<ConversionException>(result.error.errorData)
                    return ConversionResult.Fail(message.error);
                }

                is Result.Success -> {
                    val jsonString: String = result.get()
                    val map = ObjectMapper().readValue<MutableMap<Any, Any>>(jsonString)

                    if (map.containsKey("rates"))
                        currencyMap = map["rates"] as HashMap<String, Double>
                    else if(map.containsKey("error")) {
                        val error = "error ${map.get("error")}";
                        error(error)
                        return ConversionResult.Fail(error)
                    } else {
                        val error="error retrieving the exchange rate json from external API";
                        error(error)
                        return ConversionResult.Fail(error)
                    }
                }
            }
            when(currencyMap.containsKey(currencyTo)){
                true -> return ConversionResult.Success(currencyMap.getOrDefault(currencyTo,0.0))
                else -> return ConversionResult.Fail("Destination '$currencyTo' is not supported")
            }
        }
    }
}