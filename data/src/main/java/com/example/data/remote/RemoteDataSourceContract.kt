package com.example.data.remote

import com.example.domain.entity.HistoricalData
import com.example.domain.entity.LatestRate
import com.example.domain.entity.Symbols

interface RemoteDataSourceContract {

    suspend fun getSymbols(apiKey: Long): Symbols
    suspend fun getHistoricalData(date: String, apiKey: Long, base: String, symbols: List<String>): HistoricalData
    suspend fun getLatestRates(apiKey: Long, base: String, symbols: List<String>):LatestRate
}