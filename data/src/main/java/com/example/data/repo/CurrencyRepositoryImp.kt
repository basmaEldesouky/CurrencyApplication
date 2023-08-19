package com.example.data.repo

import com.example.common.Resource
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

    override suspend fun getSymbols(): Flow<Resource<Symbols>> {
        return flow {
            try {
                val symbolsData = remoteDataSource.getSymbols(BuildConfig.API_KEY)
                emit(Resource.Success(symbolsData))
            } catch (ex1: Exception) {
                emit(Resource.Error(ex1))
            }
        }
    }

    override suspend fun getHistoricalData(
        date: String,
        base: String,
        symbols: List<String>
    ): Flow<Resource<HistoricalData>> {
        return flow {
            try {
                val historicalData =
                    remoteDataSource.getHistoricalData(date, BuildConfig.API_KEY, base, symbols)
                emit(Resource.Success(historicalData))
            } catch (ex1: Exception) {
                emit(Resource.Error(ex1))
            }
        }
    }

    override suspend fun getLatestRates(base: String, symbols: List<String>): Flow<Resource<LatestRate>> {
        return flow {
            try {
                val latestRatesData = remoteDataSource.getLatestRates(BuildConfig.API_KEY, base, symbols)
                emit(Resource.Success(latestRatesData))
            } catch (ex1: Exception) {
                emit(Resource.Error(ex1))
            }

        }
    }
}