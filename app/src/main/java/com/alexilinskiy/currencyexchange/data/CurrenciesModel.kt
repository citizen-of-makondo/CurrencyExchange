package com.alexilinskiy.currencyexchange.data

data class CurrenciesModel(
    val name: String,
    val shortName: String,
    val symbol: String,
    var isSelected: Boolean
)
