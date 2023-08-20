package com.example.presentation

import androidx.test.filters.SmallTest
import com.example.common.Resource
import com.example.domain.useCase.GetHistoricalDataUseCase
import com.example.domain.useCase.GetLatestRatesUseCase
import com.example.presentation.currencyConversion.CurrencyConversionViewModel
import com.example.presentation.currencyRates.CurrencyRatesViewModel
import com.example.presentation.utils.MainCoroutineRule
import com.example.presentation.utils.TestDataGenerator
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
class CurrencyRatesViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var getLatestRatesUseCase: GetLatestRatesUseCase

    @MockK
    private lateinit var getHistoricalDataUseCase: GetHistoricalDataUseCase


    private lateinit var currencyRatesViewModel: CurrencyRatesViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create MainViewModel before every test
        currencyRatesViewModel = CurrencyRatesViewModel(
            getHistoricalDataUseCase = getHistoricalDataUseCase,
            getLatestRatesUseCase = getLatestRatesUseCase
        )
    }

    @Test
    fun test_fetch_historical_data_success() = runBlockingTest {

        val historicalData = TestDataGenerator.generateHistoricalData()
        val historicalDataFlow = flowOf(Resource.Success(historicalData))

        // Given
        coEvery { getHistoricalDataUseCase.invoke(TestDataGenerator.date, TestDataGenerator.base, TestDataGenerator.currencyList, TestDataGenerator.format) } returns historicalDataFlow

        // When && Assertions
        currencyRatesViewModel.getLatestRates(TestDataGenerator.base, TestDataGenerator.currencyList, TestDataGenerator.format)

        // Then
        coVerify { getHistoricalDataUseCase.invoke(TestDataGenerator.date, TestDataGenerator.base, TestDataGenerator.currencyList, TestDataGenerator.format) }
    }

    @Test
    fun test_fetch_lhistorical_data_fail() = runBlockingTest {

        val historicalDataFlow = flowOf(Resource.Error(Exception("error")))

        // Given
        coEvery { getHistoricalDataUseCase.invoke(TestDataGenerator.date, TestDataGenerator.base, TestDataGenerator.currencyList, TestDataGenerator.format) } returns historicalDataFlow

        // When && Assertions
        currencyRatesViewModel.getLatestRates(TestDataGenerator.base, TestDataGenerator.currencyList, TestDataGenerator.format)

        // Then
        coVerify { getHistoricalDataUseCase.invoke(TestDataGenerator.date, TestDataGenerator.base, TestDataGenerator.currencyList, TestDataGenerator.format) }
    }

    @Test
    fun test_fetch_latest_rates_success() = runBlockingTest {

        val latestRates = TestDataGenerator.generateLatestRates()
        val latestRatesFlow = flowOf(Resource.Success(latestRates))

        // Given
        coEvery { getLatestRatesUseCase.invoke(TestDataGenerator.base, TestDataGenerator.currencyList, TestDataGenerator.format) } returns latestRatesFlow

        // When && Assertions
        currencyRatesViewModel.getLatestRates(TestDataGenerator.base, TestDataGenerator.currencyList, TestDataGenerator.format)

        // Then
        coVerify { getLatestRatesUseCase.invoke(TestDataGenerator.base, TestDataGenerator.currencyList, TestDataGenerator.format) }
    }

    @Test
    fun test_fetch_latest_rates_fail() = runBlockingTest {

        val latestRatesFlow = flowOf(Resource.Error(Exception("error")))

        // Given
        coEvery { getLatestRatesUseCase.invoke(TestDataGenerator.base, TestDataGenerator.currencyList, TestDataGenerator.format) } returns latestRatesFlow

        // When && Assertions
        currencyRatesViewModel.getLatestRates(TestDataGenerator.base, TestDataGenerator.currencyList, TestDataGenerator.format)
        // Then
        coVerify { getLatestRatesUseCase.invoke(TestDataGenerator.base, TestDataGenerator.currencyList, TestDataGenerator.format) }
    }


}