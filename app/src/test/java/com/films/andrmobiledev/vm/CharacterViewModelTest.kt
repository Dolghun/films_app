package com.films.andrmobiledev.vm

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.andrmobiledev.domain.usecases.GetShowByIdUseCase
import com.andrmobiledev.domain.usecases.GetShowsUseCase
import com.andrmobiledev.presentation.uidata.ShowDetailUI
import com.andrmobiledev.presentation.uidata.utils.FeedbackCreator
import com.andrmobiledev.presentation.uidata.utils.FeedbackType
import com.andrmobiledev.presentation.uidata.utils.Resource
import com.andrmobiledev.presentation.vm.ShowsViewModel
import com.films.andrmobiledev.fakeEntityPaging
import com.films.andrmobiledev.fakeShows
import com.films.andrmobiledev.fakeUIPaging
import com.films.andrmobiledev.fakeUIShows
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ShowsViewModelTest {

    private lateinit var viewModel: ShowsViewModel
    private val getShowsUseCase: GetShowsUseCase = mockk()
    private val getShowByIdUseCase: GetShowByIdUseCase = mockk()
    private val mockFeedbackCreator: FeedbackCreator = mockk()

    @Before
    fun setup() {
        viewModel =
            ShowsViewModel(
                getShowsUseCase,
                getShowByIdUseCase,
                mockFeedbackCreator
            )
    }

    @Test
    fun `getShows - returning ui list item when usecase is success`() {
        runBlockingTest {
            coEvery {
                getShowsUseCase()
            } returns fakeEntityPaging

            viewModel.getShows()

            val job = launch {
                viewModel.showsStateFlow.first()

                Assert.assertEquals(viewModel.showsStateFlow.first(), fakeUIPaging.first())
            }

            job.cancel()
        }
    }


    @Test
    fun `verify getShowById use case emits result with data`() {
        runBlockingTest {

            coEvery {
                getShowByIdUseCase(0)
            } returns flow { fakeShows[0] }

            val job = launch {
                viewModel.goToShowDetails(0)

                val realResult = viewModel.showDetailsFlow.single()
                val expectedResult = Resource.Success(fakeUIShows[0])

                Assert.assertEquals(expectedResult, realResult)
            }

            job.cancel()
        }
    }

    @Test
    fun `verify getShowById use case return an error`() {
        runBlockingTest {

            val fakeFeedback = "Error"

            coEvery {
                getShowByIdUseCase(0)
            } throws Exception()

            coEvery {
                mockFeedbackCreator.create(FeedbackType.GENERIC_EMPTY_VIEW)
            } returns fakeFeedback

            val job = launch {
                viewModel.goToShowDetails(0)

                val realResult = viewModel.showDetailsFlow.single()
                val expectedResult = Resource.Error<ShowDetailUI>(fakeFeedback)

                Assert.assertEquals(expectedResult, realResult)
            }

            job.cancel()
        }
    }
}