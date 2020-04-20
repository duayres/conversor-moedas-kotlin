package br.duayres.app.application

sealed class ConversionResult {
    class Success(val value: Double): ConversionResult()
    class Fail(val value: String): ConversionResult()
}