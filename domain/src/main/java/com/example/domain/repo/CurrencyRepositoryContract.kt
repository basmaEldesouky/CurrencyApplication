package com.example.domain.repo

import com.example.common.Resource
import com.example.domain.entity.HistoricalData
import com.example.domain.entity.LatestRate
import com.example.domain.entity.Symbols
import kotlinx.coroutines.flow.Flow

interface CurrencyRepositoryContract {
    suspend fun getHistoricalData(date: String, base: String, symbols: String, format: Int): Flow<Resource<HistoricalData>>
    suspend fun getLatestRates(base: String, symbols: String, format: Int): Flow<Resource<LatestRate>>
}