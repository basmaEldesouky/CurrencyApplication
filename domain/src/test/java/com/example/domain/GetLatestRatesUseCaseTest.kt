package com.example.domain

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.example.common.Resource
import com.example.domain.repo.CurrencyRepositoryContract
import com.example.domain.useCase.GetLatestRatesUseCase
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
class GetLatestRatesUseCaseTest {
    @MockK
    private lateinit var repository: CurrencyRepositoryContract

    private lateinit var getLatestRatesUseCase: GetLatestRatesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        getLatestRatesUseCase = GetLatestRatesUseCase(
            repository = repository,
        )
    }

    @Test
    fun test_get_latest_rates_success() = runBlockingTest {

        val latestRates = TestDataGenerator.generateLatestRates()
        val latestRatesFlow = flowOf(Resource.Success(latestRates))

        // Given
        coEvery { repository.getLatestRates(TestDataGenerator.base, TestDataGenerator.currencyList) } returns latestRatesFlow

        // When & Assertions
        val result = getLatestRatesUseCase.invoke(TestDataGenerator.base, TestDataGenerator.currencyList)
        result.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).isEqualTo(latestRatesFlow.first().data)
            expectComplete()
        }

        // Then
        coVerify { repository.getLatestRates(TestDataGenerator.base, TestDataGenerator.currencyList) }
    }


    @Test
    fun test_get_latest_rates_fail() = runBlockingTest {

        val errorFlow = flowOf(Resource.Error(Exception()))

        // Given
        coEvery { repository.getLatestRates(TestDataGenerator.base, TestDataGenerator.currencyList) } returns errorFlow

        // When & Assertions
        val result = getLatestRatesUseCase.invoke(TestDataGenerator.base, TestDataGenerator.currencyList)
        result.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { repository.getLatestRates(TestDataGenerator.base, TestDataGenerator.currencyList) }
    }
}