package com.example.data.repo

import com.example.data.BuildConfig
import com.example.common.Resource
import com.example.domain.dataSource.RemoteDataSourceContract
import com.example.domain.entity.HistoricalData
import com.example.domain.entity.LatestRate
import com.example.domain.entity.Symbols
import com.example.domain.repo.CurrencyRepositoryContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencyRepositoryImp @Inject constructor(private val remoteDataSource: RemoteDataSourceContract): CurrencyRepositoryContract {

    override suspend fun getHistoricalData(
        date: String,
        base: String,
        symbols: String,
        format: Int
    ): Flow<Resource<HistoricalData>> {
        return flow {
            try {
                val historicalData =
                    remoteDataSource.getHistoricalData(date, BuildConfig.API_KEY, base, symbols, format)
                emit(Resource.Success(historicalData))
            } catch (ex1: Exception) {
                emit(Resource.Error(ex1))
            }
        }
    }

    override suspend fun getLatestRates(base: String, symbols: String, format: Int): Flow<Resource<LatestRate>> {
        return flow {
            try {
                val latestRatesData = remoteDataSource.getLatestRates(BuildConfig.API_KEY, base, symbols, format)
                emit(Resource.Success(latestRatesData))
            } catch (ex1: Exception) {
                emit(Resource.Error(ex1))
            }

        }
    }
}