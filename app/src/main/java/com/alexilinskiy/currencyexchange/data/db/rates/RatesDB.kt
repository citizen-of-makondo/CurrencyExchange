package com.alexilinskiy.currencyexchange.data.db.rates

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RatesModelDB::class],
    version = 1,
    exportSchema = false
)
abstract class RatesDB : RoomDatabase() {
    abstract val dao: RatesDAO
}