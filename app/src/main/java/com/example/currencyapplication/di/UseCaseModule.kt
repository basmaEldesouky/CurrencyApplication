package com.example.currencyapplication.di

import com.example.domain.repo.CurrencyRepositoryContract
import com.example.domain.useCase.GetHistoricalDataUseCase
import com.example.domain.useCase.GetLatestRatesUseCase
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