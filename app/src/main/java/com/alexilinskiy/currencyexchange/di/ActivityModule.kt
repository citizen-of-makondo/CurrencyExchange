package com.alexilinskiy.currencyexchange.di

import com.alexilinskiy.currencyexchange.domain.CurrenciesRepositoryImpl
import com.alexilinskiy.currencyexchange.domain.CurrenciesUseCaseImpl
import com.alexilinskiy.currencyexchange.domain.ICurrenciesRepository
import com.alexilinskiy.currencyexchange.domain.ICurrenciesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindCurrenciesUseCase(
        currenciesUseCaseImpl: CurrenciesUseCaseImpl
    ): ICurrenciesUseCase

    @Binds
    abstract fun bindCurreciesRepository(
        currenciesRepositoryImpl: CurrenciesRepositoryImpl
    ): ICurrenciesRepository

}