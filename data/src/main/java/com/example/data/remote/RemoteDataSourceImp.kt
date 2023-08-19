package com.example.data.remote

import com.example.domain.dataSource.RemoteDataSourceContract
import com.example.domain.entity.HistoricalData
import com.example.domain.entity.LatestRate
import com.example.domain.entity.Symbols
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val remoteServices: RemoteServices):
    RemoteDataSourceContract {

    override suspend fun getSymbols(apiKey: String): Symbols {
       return remoteServices.getSymbols(apiKey)
    }

    override suspend fun getHistoricalData(
        date: String,
        apiKey: String,
        base: String,
        symbols: List<String>
    ): HistoricalData {
        return remoteServices.getHistoricalData(date, apiKey, base, symbols)
    }

    override suspend fun getLatestRates(apiKey: String, base: String, symbols: List<String>): LatestRate {
        return remoteServices.getLatestRates(apiKey, base, symbols)
    }
}