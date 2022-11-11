package com.alexilinskiy.currencyexchange.di

import android.content.Context
import androidx.room.Room
import com.alexilinskiy.currencyexchange.data.db.currency.CurrencyDAO
import com.alexilinskiy.currencyexchange.data.db.currency.CurrencyDB
import com.alexilinskiy.currencyexchange.data.db.rates.RatesDAO
import com.alexilinskiy.currencyexchange.data.db.rates.RatesDB
import com.alexilinskiy.currencyexchange.domain.remote.CurrencyExchangeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.vatcomply.com/")
            .build()
    }

    @Provides
    fun provideRetrofitApi(retrofit: Retrofit): CurrencyExchangeApi {
        return retrofit.create(CurrencyExchangeApi::class.java)
    }

    @Provides
    fun provideRatesRoomDao(database: RatesDB): RatesDAO {
        return database.dao
    }

    @Singleton
    @Provides
    fun provideRatesRoomDatabase(
        @ApplicationContext appContext: Context
    ): RatesDB {
        return Room.databaseBuilder(
            appContext,
            RatesDB::class.java,
            "rates_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCurrencyRoomDao(database: CurrencyDB): CurrencyDAO {
        return database.dao
    }

    @Singleton
    @Provides
    fun provideCurrencyRoomDatabase(
        @ApplicationContext appContext: Context
    ): CurrencyDB {
        return Room.databaseBuilder(
            appContext,
            CurrencyDB::class.java,
            "selectedCurrency"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}