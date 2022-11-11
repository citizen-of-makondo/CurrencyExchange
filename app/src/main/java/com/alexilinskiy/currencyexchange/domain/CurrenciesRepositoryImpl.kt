package com.alexilinskiy.currencyexchange.domain

import com.alexilinskiy.currencyexchange.data.MapModel
import com.alexilinskiy.currencyexchange.data.db.currency.CurrencyDAO
import com.alexilinskiy.currencyexchange.data.db.currency.CurrencyModelDB
import com.alexilinskiy.currencyexchange.data.db.rates.RatesDAO
import com.alexilinskiy.currencyexchange.data.db.rates.RatesModelDB
import com.alexilinskiy.currencyexchange.domain.remote.CurrencyExchangeApi
import javax.inject.Inject

class CurrenciesRepositoryImpl @Inject constructor(
    private val currencyExchangeApi: CurrencyExchangeApi,
    private val currencyDAO: CurrencyDAO,
    private val ratesDAO: RatesDAO
) : ICurrenciesRepository {

    override suspend fun getAllCurrenciesFromRemote() {

        val data = currencyExchangeApi.getAllCurrencies()
        val list = MapModel.mapCurrenciesToModel(data)

        list.forEach {
            currencyDAO.addCurrency(
                CurrencyModelDB(name = it.name, shortName = it.shortName, symbol = it.symbol, isSelected = false)
            )
        }
    }

    override suspend fun getAllCurrenciesFromLocal(): List<CurrencyModelDB> {
        return currencyDAO.getCurrency()
    }

    override suspend fun getRates(
        base: String,
        currenciesList: List<CurrencyModelDB>
    ): List<RatesModelDB> {
        val data = currencyExchangeApi.getRates(base)
        val list = MapModel.mapRatesToModel(data, currenciesList)
        val dataFromDB = ratesDAO.getRates()

        if (dataFromDB.isNotEmpty()) {
            dataFromDB.forEach { rateDB ->
                val updatedRate = RatesModelDB(
                    name = rateDB.name,
                    shortName = rateDB.shortName,
                    symbol = rateDB.symbol,
                    count = list.find { it.shortName == rateDB.shortName }?.count ?: 0.0,
                    isFavorite = rateDB.isFavorite
                )
                ratesDAO.updateRate(updatedRate)
            }
        } else {
            list.forEach {
                ratesDAO.addRate(
                    RatesModelDB(
                        name = it.name,
                        shortName = it.shortName,
                        symbol = it.symbol,
                        count = it.count,
                        isFavorite = it.isFavotite
                    )
                )
            }
        }
        return ratesDAO.getRates()
    }

    override suspend fun onFavoriteIconCLick(rate: RatesModelDB): List<RatesModelDB> {
        ratesDAO.updateRate(rate)
        return ratesDAO.getRates()
    }

    override suspend fun getFavoritesRateList(): List<RatesModelDB> {
        return ratesDAO.getFavoritesRatesList()
    }

    override suspend fun setCurrencyChanged(changedCurrency: CurrencyModelDB) {
        val data = currencyDAO.getCurrency()
        val result: MutableList<CurrencyModelDB> = mutableListOf()
        result.addAll(data.sortedByDescending { it.name == changedCurrency.name })

        result.forEach {
            currencyDAO.addCurrency(
                CurrencyModelDB(
                    name = it.name,
                    shortName = it.shortName,
                    symbol = it.symbol,
                    isSelected = changedCurrency.isSelected
                )
            )
        }
    }

    override suspend fun getFilteredList(): List<CurrencyModelDB> {
        return currencyDAO.getCurrency()
    }

}