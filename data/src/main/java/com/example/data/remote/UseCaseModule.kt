package com.example.data.remote

import com.example.domain.repo.CurrencyRepositoryContract
import com.example.domain.useCase.GetHistoricalDataUseCase
import com.example.domain.useCase.GetLatestRatesUseCase
import com.example.domain.useCase.GetSymbolsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetSymbolsUseCase(
        currencyRepository: CurrencyRepositoryContract
    ): GetSymbolsUseCase =
        GetSymbolsUseCase(currencyRepository)


    @Singleton
    @Provides
    fun provideGetHistoricalDataUseCase(
        currencyRepository: CurrencyRepositoryContract
    ): GetHistoricalDataUseCase =
        GetHistoricalDataUseCase(currencyRepository)

    @Singleton
    @Provides
    fun provideLatestRatesUseCase(
        currencyRepository: CurrencyRepositoryContract
    ): GetLatestRatesUseCase =
        GetLatestRatesUseCase(currencyRepository)

}