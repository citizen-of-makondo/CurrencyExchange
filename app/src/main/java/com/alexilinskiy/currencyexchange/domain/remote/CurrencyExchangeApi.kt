package com.alexilinskiy.currencyexchange.domain.remote

import com.alexilinskiy.currencyexchange.data.Currencies
import com.alexilinskiy.currencyexchange.data.Rates
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyExchangeApi {

    @GET("currencies")
    suspend fun getAllCurrencies(): Currencies

    @GET("rates")
    suspend fun getRates(@Query("base") base: String): Rates

}