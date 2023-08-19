package com.example.domain.useCase

import com.example.common.Resource
import com.example.domain.entity.Symbols
import com.example.domain.repo.CurrencyRepositoryContract
import kotlinx.coroutines.flow.Flow

class GetSymbolsUseCase(private val repository: CurrencyRepositoryContract) {
    suspend fun invoke(): Flow<Resource<Symbols>> {
        return repository.getSymbols()
    }
}