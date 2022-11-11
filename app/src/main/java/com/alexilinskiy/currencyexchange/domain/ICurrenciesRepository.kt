package com.alexilinskiy.currencyexchange.domain

import com.alexilinskiy.currencyexchange.data.db.currency.CurrencyModelDB
import com.alexilinskiy.currencyexchange.data.db.rates.RatesModelDB

interface ICurrenciesRepository {

    suspend fun getAllCurrenciesFromRemote()

    suspend fun getAllCurrenciesFromLocal(): List<CurrencyModelDB>

    suspend fun getRates(base: String, currenciesList: List<CurrencyModelDB>): List<RatesModelDB>

    suspend fun onFavoriteIconCLick(rate: RatesModelDB): List<RatesModelDB>

    suspend fun getFavoritesRateList(): List<RatesModelDB>

    suspend fun setCurrencyChanged(changedCurrency: CurrencyModelDB)

    suspend fun getFilteredList(): List<CurrencyModelDB>
}