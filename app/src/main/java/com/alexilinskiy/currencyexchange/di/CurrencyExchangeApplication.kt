package com.alexilinskiy.currencyexchange.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CurrencyExchangeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}