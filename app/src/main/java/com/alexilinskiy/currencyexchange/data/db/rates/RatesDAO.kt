package com.alexilinskiy.currencyexchange.data.db.rates

import androidx.room.*

@Dao
interface RatesDAO {
    @Query("SELECT * FROM ratesList")
    suspend fun getRates(): List<RatesModelDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRate(rate: RatesModelDB)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRate(rate: RatesModelDB)

    @Query("SELECT * FROM ratesList WHERE isFavorite = 1")
    suspend fun getFavoritesRatesList(): List<RatesModelDB>
}