package com.alexilinskiy.currencyexchange.data.db.currency

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import java.util.Currency

@Dao
interface CurrencyDAO {
    @Query("SELECT * FROM selectedCurrency")
    suspend fun getCurrency(): List<CurrencyModelDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCurrency(currency: CurrencyModelDB)

    @Update
    suspend fun updateCurrency(currency: List<CurrencyModelDB>)

}