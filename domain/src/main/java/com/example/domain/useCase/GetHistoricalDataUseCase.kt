package com.example.domain.useCase

import com.example.common.Resource
import com.example.domain.entity.HistoricalData
import com.example.domain.repo.CurrencyRepositoryContract
import kotlinx.coroutines.flow.Flow

class GetHistoricalDataUseCase(private val repository: CurrencyRepositoryContract) {
    suspend fun invoke(date: String, base: String, symbols: String, format: Int): Flow<Resource<HistoricalData>> {
        return repository.getHistoricalData(date, base, symbols, format)
    }
}