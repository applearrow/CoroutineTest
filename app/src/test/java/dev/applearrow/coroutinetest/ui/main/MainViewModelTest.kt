package dev.applearrow.coroutinetest.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import kotlin.system.measureTimeMillis

@ExperimentalCoroutinesApi
class MainViewModelTest {

    // Since postValue switches to the main thread, we need to swap the background executor used
    // by architecture components with a different one which executes each task synchronously.
    @get:Rule
    val liveDataRule = InstantTaskExecutorRule()

    /**
     * Using runBlocking the test works, but takes 5 precious seconds to run.
     */
    @Test
    fun `ViewModel posts likes on live data`() {
        val testContextProvider = TestCoroutineProvider()
        val viewModel = MainViewModel(testContextProvider)

        val costTimeMillis = measureTimeMillis {
            viewModel.launchLikeCount()
            testContextProvider.testCoroutineDispatcher.advanceUntilIdle()

            Assert.assertEquals("0 likes", viewModel.likesLiveData.value)
        }
        print("costTimeMillis: $costTimeMillis")
    }
}