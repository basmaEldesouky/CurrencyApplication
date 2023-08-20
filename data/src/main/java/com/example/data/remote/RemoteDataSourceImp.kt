package com.example.data.remote

import com.example.domain.dataSource.RemoteDataSourceContract
import com.example.domain.entity.HistoricalData
import com.example.domain.entity.LatestRate
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val remoteServices: RemoteServices):
    RemoteDataSourceContract {

    override suspend fun getHistoricalData(
        date: String,
        apiKey: String,
        base: String,
        symbols: String,
        format: Int
    ): HistoricalData {
        return remoteServices.getHistoricalData(date, apiKey, base, symbols, format)
    }

    override suspend fun getLatestRates(apiKey: String, base: String, symbols: String, format: Int): LatestRate {
        return remoteServices.getLatestRates(apiKey, base, symbols, format)
    }
}