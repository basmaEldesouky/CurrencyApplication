package com.example.domain.useCase

import com.example.common.Resource
import com.example.domain.entity.LatestRate
import com.example.domain.repo.CurrencyRepositoryContract
import kotlinx.coroutines.flow.Flow

class GetLatestRatesUseCase(private val repository: CurrencyRepositoryContract) {
    suspend fun invoke(base: String, symbols: String, format: Int): Flow<Resource<LatestRate>> {
        return repository.getLatestRates(base, symbols, format)
    }
}