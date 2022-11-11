package com.alexilinskiy.currencyexchange.data

data class RatesModel(
    val name: String,
    val shortName: String,
    val symbol: String,
    val count: Double,
    var isFavotite: Boolean = false
)
