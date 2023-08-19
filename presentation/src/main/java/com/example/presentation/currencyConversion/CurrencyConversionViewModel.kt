package com.example.presentation.currencyConversion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.useCase.GetSymbolsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyConversionViewModel @Inject constructor(
    private val getSymbolsUseCase: GetSymbolsUseCase
): ViewModel() {

    private fun getCurrencySymbols(){
        viewModelScope.launch {
            try {

            }catch (e: Exception){

            }
        }
    }
}