package com.example.data

import androidx.test.filters.SmallTest
import com.example.data.remote.RemoteDataSourceImp
import com.example.data.remote.RemoteServices
import com.example.data.utils.TestDataGenerator
import com.example.domain.dataSource.RemoteDataSourceContract
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
class RemoteDataSourceImpTest {
    @MockK
    private lateinit var remoteServices: RemoteServices
    private lateinit var remoteDataSource: RemoteDataSourceContract

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RemoteDataSourceImp before every test
        remoteDataSource = RemoteDataSourceImp(
            remoteServices = remoteServices
        )
    }

    @Test
    fun test_get_data_historical_success() = runBlockingTest {

        val historicalDataResponse = TestDataGenerator.generateHistoricalData()

        // Given
        coEvery { remoteServices.getHistoricalData(TestDataGenerator.date,
            TestDataGenerator.apiKey,
            TestDataGenerator.base,
            TestDataGenerator.currencyList)
        } returns historicalDataResponse

        // When
        val result = remoteDataSource.getHistoricalData(TestDataGenerator.date, TestDataGenerator.apiKey, TestDataGenerator.base, TestDataGenerator.currencyList)

        // Then
        coVerify { remoteServices.getHistoricalData(TestDataGenerator.date, TestDataGenerator.apiKey, TestDataGenerator.base, TestDataGenerator.currencyList) }

        // Assertion
        val expected = historicalDataResponse
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_data_historical_fail() = runBlockingTest {

        // Given
        coEvery { remoteServices.getHistoricalData(TestDataGenerator.date,
            TestDataGenerator.apiKey,
            TestDataGenerator.base,
            TestDataGenerator.currencyList)
        } throws Exception()

        // When
        remoteDataSource.getHistoricalData(TestDataGenerator.date, TestDataGenerator.apiKey, TestDataGenerator.base, TestDataGenerator.currencyList)

        // Then
        coVerify { remoteServices.getHistoricalData(TestDataGenerator.date, TestDataGenerator.apiKey, TestDataGenerator.base, TestDataGenerator.currencyList) }

    }

    @Test
    fun test_get_latest_rates_success() = runBlockingTest {

        val latestRatesResponse = TestDataGenerator.generateLatestRates()
        // Given
        coEvery { remoteServices.getLatestRates(TestDataGenerator.apiKey, TestDataGenerator.base, TestDataGenerator.currencyList) } returns latestRatesResponse

        // When
        val result = remoteDataSource.getLatestRates(TestDataGenerator.apiKey, TestDataGenerator.base, TestDataGenerator.currencyList)

        // Then
        coVerify { remoteServices.getLatestRates(TestDataGenerator.apiKey, TestDataGenerator.base, TestDataGenerator.currencyList) }

        // Assertion
        val expected = latestRatesResponse
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_latest_rates_fail() = runBlockingTest {

        // Given
        coEvery { remoteServices.getLatestRates(TestDataGenerator.apiKey, TestDataGenerator.base, TestDataGenerator.currencyList) } throws Exception()

        // When
        remoteDataSource.getLatestRates(TestDataGenerator.apiKey, TestDataGenerator.base, TestDataGenerator.currencyList)

        // Then
        coVerify { remoteServices.getLatestRates(TestDataGenerator.apiKey, TestDataGenerator.base, TestDataGenerator.currencyList) }

    }
}