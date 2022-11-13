package com.alexilinskiy.currencyexchange.data.db.currency

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "selectedCurrency", indices = [Index(value = ["shortName"], unique = true)])
data class CurrencyModelDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "shortName")
    val shortName: String,
    @ColumnInfo(name = "symbol")
    val symbol: String,
    @ColumnInfo(name = "isSelected")
    val isSelected: Boolean = false
)
