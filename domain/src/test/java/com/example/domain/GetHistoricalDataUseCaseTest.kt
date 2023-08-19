package com.example.domain

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.example.common.Resource
import com.example.domain.repo.CurrencyRepositoryContract
import com.example.domain.useCase.GetHistoricalDataUseCase
import com.example.domain.utils.TestDataGenerator
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class GetHistoricalDataUseCaseTest {

    @MockK
    private lateinit var repository: CurrencyRepositoryContract

    private lateinit var getHistoricalDataUseCase: GetHistoricalDataUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        getHistoricalDataUseCase = GetHistoricalDataUseCase(
            repository = repository,
        )
    }

    @Test
    fun test_get_historical_data_success() = runBlockingTest {

        val historicalData = TestDataGenerator.generateHistoricalData()
        val historicalDataFlow = flowOf(Resource.Success(historicalData))

        // Given
        coEvery { repository.getHistoricalData(TestDataGenerator.date, TestDataGenerator.base, TestDataGenerator.currencyList) } returns historicalDataFlow

        // When & Assertions
        val result = getHistoricalDataUseCase.invoke(TestDataGenerator.date, TestDataGenerator.base, TestDataGenerator.currencyList)
        result.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).isEqualTo(historicalDataFlow.first().data)
            expectComplete()
        }

        // Then
        coVerify { repository.getHistoricalData(TestDataGenerator.date, TestDataGenerator.base, TestDataGenerator.currencyList) }
    }


    @Test
    fun test_get_historical_data_fail() = runBlockingTest {

        val errorFlow = flowOf(Resource.Error(Exception()))

        // Given
        coEvery { repository.getHistoricalData(TestDataGenerator.date, TestDataGenerator.base, TestDataGenerator.currencyList) } returns errorFlow

        // When & Assertions
        val result = getHistoricalDataUseCase.invoke(TestDataGenerator.date, TestDataGenerator.base, TestDataGenerator.currencyList)
        result.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { repository.getHistoricalData(TestDataGenerator.date, TestDataGenerator.base, TestDataGenerator.currencyList) }
    }
}