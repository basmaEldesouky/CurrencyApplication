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

    /*@Test
    fun test_fetch_challenges_list_success() = runBlockingTest {

        val challenges = TestDataGenerator.generateListOfChallengeItem()
        val challengesFlow = flowOf(Resource.Success(challenges))

        // Given
        coEvery { GetLatestRatesUseCase.invoke() } returns challengesFlow

        // When && Assertions
        currencyConversionViewModel.uiState.test {
            currencyConversionViewModel.setEvent(ChallengesListContract.Event.OnFetchChallenges)
            // Expect Resource.Idle from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                ChallengesListContract.State(
                    challengesState = ChallengesListContract.ChallengesState.Idle,
                )
            )
            // Expect Resource.Loading
            Truth.assertThat(expectItem()).isEqualTo(
                ChallengesListContract.State(
                    challengesState = ChallengesListContract.ChallengesState.Loading,
                )
            )
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData =
                (expected.challengesState as ChallengesListContract.ChallengesState.Success).data
            Truth.assertThat(expected).isEqualTo(
                ChallengesListContract.State(
                    challengesState = ChallengesListContract.ChallengesState.Success(
                        generalChallengeItemUiMapper.fromList(challenges)
                    ),
                )
            )
            Truth.assertThat(expectedData)
                .containsExactlyElementsIn(generalChallengeItemUiMapper.fromList(challenges))


            //Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }


        // Then
        coVerify { GetLatestRatesUseCase.invoke() }
    }

    @Test
    fun test_fetch_challenges_list_fail() = runBlockingTest {

        val challengesErrorFlow = flowOf(Resource.Error(Exception("error")))

        // Given
        coEvery { GetLatestRatesUseCase.invoke() } returns challengesErrorFlow

        // When && Assertions (UiState)
        currencyConversionViewModel.uiState.test {
            // Call method inside of this scope
            currencyConversionViewModel.setEvent(ChallengesListContract.Event.OnFetchChallenges)
            // Expect Resource.Idle from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                ChallengesListContract.State(
                    challengesState = ChallengesListContract.ChallengesState.Idle,
                    selectedChallenge = null
                )
            )
            // Expect Resource.Loading
            Truth.assertThat(expectItem()).isEqualTo(
                ChallengesListContract.State(
                    challengesState = ChallengesListContract.ChallengesState.Loading,
                    selectedChallenge = null
                )
            )
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }

        // When && Assertions (UiEffect)
        currencyConversionViewModel.effect.test {
            // Expect ShowError Effect
            val expected = expectItem()
            val expectedData = (expected as ChallengesListContract.Effect.ShowError).message
            Truth.assertThat(expected).isEqualTo(
                ChallengesListContract.Effect.ShowError("error")
            )
            Truth.assertThat(expectedData).isEqualTo("error")
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }


        // Then
        coVerify { GetLatestRatesUseCase.invoke() }
    }
    @Test
    fun test_select_challenge() = runBlockingTest {

        val generalChallengeItem = TestDataGenerator.generateGeneralChallengeItem()

        // Given (no-op)

        // When && Assertions
        currencyConversionViewModel.uiState.test {
            // Call method inside of this scope
            // For more info, see https://github.com/cashapp/turbine/issues/19
            currencyConversionViewModel.setEvent(ChallengesListContract.Event.OnChallengeClicked(challenge = generalChallengeItemUiMapper.from(generalChallengeItem)))
            // Expect Resource.Idle from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                ChallengesListContract.State(
                    challengesState = ChallengesListContract.ChallengesState.Idle,
                    selectedChallenge = null
                )
            )
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = expected.selectedChallenge
            Truth.assertThat(expected).isEqualTo(
                ChallengesListContract.State(
                    challengesState = ChallengesListContract.ChallengesState.Idle,
                    selectedChallenge  = generalChallengeItemUiMapper.from(generalChallengeItem)
                )
            )
            Truth.assertThat(expectedData).isEqualTo(generalChallengeItemUiMapper.from(generalChallengeItem))
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }


        // Then (no-op)
    }
*/}