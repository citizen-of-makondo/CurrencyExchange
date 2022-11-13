package com.alexilinskiy.currencyexchange.data.db.rates

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ratesList")
data class RatesModelDB(
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @PrimaryKey()
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "shortName")
    val shortName: String,
    @ColumnInfo(name = "symbol")
    val symbol: String,
    @ColumnInfo(name = "count")
    val count: Double,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean
)

