package com.alexilinskiy.currencyexchange.data.db.currency

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CurrencyModelDB::class],
    version = 1,
    exportSchema = false
)
abstract class CurrencyDB : RoomDatabase() {
    abstract val dao: CurrencyDAO
}