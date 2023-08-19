package com.example.data

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.example.common.Resource
import com.example.data.repo.CurrencyRepositoryImp
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
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class RepositoryImpTest {

    @MockK
    private lateinit var remoteDataSource: RemoteDataSourceContract

    private lateinit var repository: CurrencyRepositoryImp

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RepositoryImp before every test
        repository = CurrencyRepositoryImp(
            remoteDataSource = remoteDataSource
        )
    }

    @Test
    fun test_get_historical_data_from_remote_success() = runBlockingTest {

        val historicalData = TestDataGenerator.generateHistoricalData()

        // Given
        coEvery { remoteDataSource.getHistoricalData(TestDataGenerator.date,
            TestDataGenerator.apiKey,
            TestDataGenerator.base,
            TestDataGenerator.currencyList)
        } returns historicalData

        // When & Assertions
        val flow = repository.getHistoricalData(TestDataGenerator.date, TestDataGenerator.base, TestDataGenerator.currencyList)
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).isEqualTo(historicalData)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSource.getHistoricalData(TestDataGenerator.date, TestDataGenerator.apiKey, TestDataGenerator.base, TestDataGenerator.currencyList) }
    }


    @Test
    fun test_for_error_state_when_remote_fail_of_getting_historical_data() = runBlockingTest {
        // Given
        coEvery { remoteDataSource.getHistoricalData(TestDataGenerator.date,
            TestDataGenerator.apiKey,
            TestDataGenerator.base,
            TestDataGenerator.currencyList)
        } throws Exception()

        // When && Assertions
        val flow = repository.getHistoricalData(TestDataGenerator.date, TestDataGenerator.base, TestDataGenerator.currencyList)
        flow.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSource.getHistoricalData(TestDataGenerator.date, TestDataGenerator.apiKey, TestDataGenerator.base, TestDataGenerator.currencyList) }
    }


    @Test
    fun test_get_latest_rates_details_from_remote_success() = runBlockingTest {
        val latestRates = TestDataGenerator.generateLatestRates()

        // Given
        coEvery { remoteDataSource.getLatestRates(TestDataGenerator.apiKey, TestDataGenerator.base, TestDataGenerator.currencyList) } returns latestRates

        // When & Assertions
        val flow = repository.getLatestRates(TestDataGenerator.base, TestDataGenerator.currencyList)
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).isEqualTo(latestRates)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSource.getLatestRates(TestDataGenerator.apiKey, TestDataGenerator.base, TestDataGenerator.currencyList) }
    }


    @Test
    fun test_error_state_when_remote_fail_of_getting_challenge_latest_rates() =
        runBlockingTest {
            // Given
            coEvery { remoteDataSource.getLatestRates(TestDataGenerator.apiKey, TestDataGenerator.base, TestDataGenerator.currencyList) } throws Exception()

            // When && Assertions
            val flow = repository.getLatestRates(TestDataGenerator.base, TestDataGenerator.currencyList)
            flow.test {
                // Expect Resource.Error
                Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
                expectComplete()
            }

            // Then
            coVerify { remoteDataSource.getLatestRates(TestDataGenerator.apiKey, TestDataGenerator.base, TestDataGenerator.currencyList) }
        }
}