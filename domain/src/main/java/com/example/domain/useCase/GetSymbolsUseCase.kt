package com.example.domain.useCase

import com.example.domain.entity.Symbols
import com.example.domain.repo.CurrencyRepositoryContract
import kotlinx.coroutines.flow.Flow

class GetSymbolsUseCase(private val repository: CurrencyRepositoryContract) {
    suspend fun invoke(): Flow<Symbols> {
        return repository.getSymbols()
    }
}