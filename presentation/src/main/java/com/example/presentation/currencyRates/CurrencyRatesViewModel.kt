package com.example.presentation.currencyRates

import androidx.lifecycle.ViewModel
import com.example.domain.useCase.GetHistoricalDataUseCase
import com.example.domain.useCase.GetLatestRatesUseCase
import com.example.presentation.currencyConversion.CurrencyConversionViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CurrencyRatesViewModel @Inject constructor(
    private val getHistoricalDataUseCase: GetHistoricalDataUseCase,
    private val getLatestRatesUseCase: GetLatestRatesUseCase
): ViewModel() {

    fun getHistoricalData(){

    }

    fun getLatestRates(){

    }

}