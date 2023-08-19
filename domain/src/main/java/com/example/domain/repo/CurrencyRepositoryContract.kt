package com.example.domain.repo

import com.example.common.Resource
import com.example.domain.entity.HistoricalData
import com.example.domain.entity.LatestRate
import com.example.domain.entity.Symbols
import kotlinx.coroutines.flow.Flow

interface CurrencyRepositoryContract {

    suspend fun getSymbols(): Flow<Resource<Symbols>>
    suspend fun getHistoricalData(date: String, base: String, symbols: List<String>): Flow<Resource<HistoricalData>>
    suspend fun getLatestRates(base: String, symbols: List<String>): Flow<Resource<LatestRate>>
}