package com.example.domain.useCase

import com.example.domain.entity.LatestRate
import com.example.domain.repo.CurrencyRepositoryContract
import kotlinx.coroutines.flow.Flow

class GetLatestRates(private val repository: CurrencyRepositoryContract) {
    suspend fun invoke(): Flow<LatestRate> {
        return repository.getLatestRates()
    }
}