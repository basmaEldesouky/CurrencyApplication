package com.example.presentation.currencyRates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Resource
import com.example.domain.entity.HistoricalData
import com.example.domain.entity.LatestRate
import com.example.domain.useCase.GetHistoricalDataUseCase
import com.example.domain.useCase.GetLatestRatesUseCase
import com.example.presentation.currencyConversion.CurrencyConversionViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.round

@HiltViewModel
class CurrencyRatesViewModel @Inject constructor(
    private val getHistoricalDataUseCase: GetHistoricalDataUseCase,
    private val getLatestRatesUseCase: GetLatestRatesUseCase
): ViewModel() {

    private val _historicalData: MutableStateFlow<HistoricalData?> = MutableStateFlow(null)
    val historicalData: StateFlow<HistoricalData?> = _historicalData

    private val _latestRates: MutableStateFlow<LatestRate?> = MutableStateFlow(null)
    val latestRates: StateFlow<LatestRate?> = _latestRates


    fun getHistoricalData(date: String, toCurrency: String, fromCurrency: String, format: Int){
        viewModelScope.launch{
            try {
                getHistoricalDataUseCase.invoke(date, toCurrency, fromCurrency, format)
                    .onStart { emit(Resource.Loading) }
                    .collect {
                        when (it) {
                            is Resource.Loading -> {}
                            is Resource.Success -> { _historicalData.value = it.data }
                            is Resource.Error -> {}
                            else -> {}
                        }
                    }
            } catch (e: Exception){ }
        }
    }

    fun getLatestRates(toCurrency: String, fromCurrency: String, format: Int){
        viewModelScope.launch{
            try {
                getLatestRatesUseCase.invoke(toCurrency, fromCurrency, format)
                    .onStart { emit(Resource.Loading) }
                    .collect {
                        when (it) {
                            is Resource.Loading -> {}
                            is Resource.Success -> { _latestRates.value = it.data }
                            is Resource.Error -> {}
                            else -> {}
                        }
                    }
            } catch (e: Exception){ }
        }
    }

}