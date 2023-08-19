package com.example.presentation

import androidx.test.filters.SmallTest
import com.example.presentation.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class CurrencyRatesViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()
}