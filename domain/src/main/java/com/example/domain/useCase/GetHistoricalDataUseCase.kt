package com.example.domain.useCase

import com.example.domain.entity.HistoricalData
import com.example.domain.repo.CurrencyRepositoryContract
import kotlinx.coroutines.flow.Flow

class GetHistoricalDataUseCase(private val repository: CurrencyRepositoryContract) {
    suspend fun invoke(): Flow<HistoricalData> {
        return repository.getHistoricalData()
    }
}