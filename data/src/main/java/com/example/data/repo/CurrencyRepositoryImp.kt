package com.example.data.repo

import com.example.data.BuildConfig
import com.example.data.remote.RemoteDataSourceContract
import com.example.data.remote.RemoteServices
import com.example.domain.entity.HistoricalData
import com.example.domain.entity.LatestRate
import com.example.domain.entity.Symbols
import com.example.domain.repo.CurrencyRepositoryContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencyRepositoryImp @Inject constructor(private val remoteDataSource: RemoteDataSourceContract): CurrencyRepositoryContract {

    override suspend fun getSymbols(): Flow<Symbols> {
        return flow {
            val symbolsData = remoteDataSource.getSymbols(BuildConfig.API_KEY)
            emit(symbolsData)
        }
    }

    override suspend fun getHistoricalData(
        date: String,
        base: String,
        symbols: List<String>
    ): Flow<HistoricalData> {
        return flow {
            val historicalData = remoteDataSource.getHistoricalData(date, BuildConfig.API_KEY, base, symbols)
            emit(historicalData)
        }
    }

    override suspend fun getLatestRates(base: String, symbols: List<String>): Flow<LatestRate> {
        return flow {
            val latestRatesData = remoteDataSource.getLatestRates(BuildConfig.API_KEY, base, symbols)
            emit(latestRatesData)
        }
    }
}