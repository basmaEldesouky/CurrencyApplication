package com.example.domain.dataSource

import com.example.domain.entity.HistoricalData
import com.example.domain.entity.LatestRate
import com.example.domain.entity.Symbols

interface RemoteDataSourceContract {

    suspend fun getSymbols(apiKey: String): Symbols
    suspend fun getHistoricalData(date: String, apiKey: String, base: String, symbols: List<String>): HistoricalData
    suspend fun getLatestRates(apiKey: String, base: String, symbols: List<String>):LatestRate
}