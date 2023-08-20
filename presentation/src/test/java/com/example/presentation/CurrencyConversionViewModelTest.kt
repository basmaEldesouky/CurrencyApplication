package com.example.presentation

import androidx.test.filters.SmallTest
import com.example.common.Resource
import com.example.domain.useCase.GetLatestRatesUseCase
import com.example.presentation.currencyConversion.CurrencyConversionViewModel
import com.example.presentation.utils.MainCoroutineRule
import com.example.presentation.utils.TestDataGenerator
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class CurrencyConversionViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var getLatestRatesUseCase: GetLatestRatesUseCase


    private lateinit var currencyConversionViewModel: CurrencyConversionViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create MainViewModel before every test
        currencyConversionViewModel = CurrencyConversionViewModel(
            getLatestRatesUseCase = getLatestRatesUseCase
        )
    }

    @Test
    fun test_fetch_latest_rates_success() = runBlockingTest {

        val latestRates = TestDataGenerator.generateLatestRates()
        val latestRatesFlow = flowOf(Resource.Success(latestRates))

        // Given
        coEvery { getLatestRatesUseCase.invoke(TestDataGenerator.base, TestDataGenerator.currencyList, TestDataGenerator.format) } returns latestRatesFlow

        // When && Assertions
       currencyConversionViewModel.getCurrencyRates(TestDataGenerator.fromAmount, TestDataGenerator.base, TestDataGenerator.currencyList)

        // Then
        coVerify { getLatestRatesUseCase.invoke(TestDataGenerator.base, TestDataGenerator.currencyList, TestDataGenerator.format) }
    }

    @Test
    fun test_fetch_latest_rates_fail() = runBlockingTest {

        val latestRatesFlow = flowOf(Resource.Error(Exception("error")))

        // Given
        coEvery { getLatestRatesUseCase.invoke(TestDataGenerator.base, TestDataGenerator.currencyList, TestDataGenerator.format) } returns latestRatesFlow

        // When && Assertions
        currencyConversionViewModel.getCurrencyRates(TestDataGenerator.fromAmount, TestDataGenerator.base, TestDataGenerator.currencyList)
        // Then
        coVerify { getLatestRatesUseCase.invoke(TestDataGenerator.base, TestDataGenerator.currencyList, TestDataGenerator.format) }
    }
}