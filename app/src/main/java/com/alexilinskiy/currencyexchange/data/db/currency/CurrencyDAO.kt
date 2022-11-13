package com.alexilinskiy.currencyexchange.data.db.currency

import androidx.room.*

@Dao
interface CurrencyDAO {
    @Query("SELECT * FROM selectedCurrency")
    suspend fun getCurrency(): List<CurrencyModelDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCurrency(currency: CurrencyModelDB)

    @Update
    suspend fun updateCurrency(currency: List<CurrencyModelDB>)

}