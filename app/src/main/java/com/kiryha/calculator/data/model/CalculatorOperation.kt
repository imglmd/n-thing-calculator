package com.kiryha.calculator.data.model

sealed class CalculatorOperation(val symbol: String) {
    object Add: CalculatorOperation("+")
    object Subtract: CalculatorOperation("-")
    object Multiply: CalculatorOperation("x")
    object Divide: CalculatorOperation("/")
    object Power: CalculatorOperation("^")
    object Percent: CalculatorOperation("%")
}