package com.example.domain.repo

import com.example.domain.entity.HistoricalData
import com.example.domain.entity.LatestRate
import com.example.domain.entity.Symbols
import kotlinx.coroutines.flow.Flow

interface CurrencyRepositoryContract {

    suspend fun getSymbols(): Flow<Symbols>
    suspend fun getHistoricalData(): Flow<HistoricalData>
    suspend fun getLatestRates(): Flow<LatestRate>
}