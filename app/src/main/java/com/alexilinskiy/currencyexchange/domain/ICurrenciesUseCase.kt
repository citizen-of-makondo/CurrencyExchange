package com.alexilinskiy.currencyexchange.domain

import com.alexilinskiy.currencyexchange.data.db.currency.CurrencyModelDB
import com.alexilinskiy.currencyexchange.data.db.rates.RatesModelDB

interface ICurrenciesUseCase {

    suspend fun getAllCurrenciesFromRemote()

    suspend fun getAllCurrenciesFromLocal(): List<CurrencyModelDB>

    suspend fun getRates(base: String, list: List<CurrencyModelDB>): List<RatesModelDB>

    suspend fun onFavoriteIconClick(rate: RatesModelDB): List<RatesModelDB>

    suspend fun getFavoritesRateList(): List<RatesModelDB>

    suspend fun setCurrencyChanged(changedCurrency: CurrencyModelDB)

    suspend fun getFilteredList(): List<CurrencyModelDB>

}