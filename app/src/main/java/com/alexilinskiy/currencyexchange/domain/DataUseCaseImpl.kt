package com.alexilinskiy.currencyexchange.domain

import com.alexilinskiy.currencyexchange.data.db.currency.CurrencyModelDB
import com.alexilinskiy.currencyexchange.data.db.rates.RatesModelDB
import javax.inject.Inject

class DataUseCaseImpl @Inject constructor(
    private val currenciesRepository: IRepository
) : IDataUseCase {

    override suspend fun getAllCurrenciesFromRemote() {
        currenciesRepository.getAllCurrenciesFromRemote()
    }

    override suspend fun getAllCurrenciesFromLocal(): List<CurrencyModelDB> {
        return currenciesRepository.getAllCurrenciesFromLocal()
    }

    override suspend fun getRates(base: String, list: List<CurrencyModelDB>): List<RatesModelDB> {
        return currenciesRepository.getRates(base, list)
    }

    override suspend fun onFavoriteIconClick(rate: RatesModelDB): List<RatesModelDB> {
        return currenciesRepository.onFavoriteIconCLick(rate)
    }

    override suspend fun getFavoritesRateList(): List<RatesModelDB> {
     return currenciesRepository.getFavoritesRateList()
    }

    override suspend fun setCurrencyChanged(changedCurrency: CurrencyModelDB) {
        currenciesRepository.setCurrencyChanged(changedCurrency)
    }

    override suspend fun getFilteredList(): List<CurrencyModelDB> {
        return currenciesRepository.getFilteredList()
    }

}