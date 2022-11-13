package com.alexilinskiy.currencyexchange.di

import com.alexilinskiy.currencyexchange.domain.RepositoryImpl
import com.alexilinskiy.currencyexchange.domain.DataUseCaseImpl
import com.alexilinskiy.currencyexchange.domain.IRepository
import com.alexilinskiy.currencyexchange.domain.IDataUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindCurrenciesUseCase(
        currenciesUseCaseImpl: DataUseCaseImpl
    ): IDataUseCase

    @Binds
    abstract fun bindCurreciesRepository(
        repository: RepositoryImpl
    ): IRepository

}