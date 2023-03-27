/*
 * Created by Ali Kabiri on 18.3.2023.
 * Copyright (c) 2023 Trusted Shops GmbH
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.etrusted.android.trustbadge.library.ui.badge

import com.etrusted.android.trustbadge.library.common.internal.getFakeTrustbadgeRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TrustbadgeViewModelAndroidTest {

    @Test
    fun testFetchTrustbadgeDataWorksWithRepoFetchSuccessful() = runTest {

        // arrange
        val testDispatcher = StandardTestDispatcher(testScheduler)
        val fakeTrustbadgeRepo = getFakeTrustbadgeRepository()
        val sut = TrustbadgeViewModel(
            scope = this,
            dispatcherIO = testDispatcher,
            dispatcherMain = testDispatcher,
            trustbadgeRepo = fakeTrustbadgeRepo,
        )

        // act
        sut.fetchTrustbadgeData("fakeString", "fakeString")
        advanceUntilIdle()

        // assert
        assertThat(sut.trustbadgeData.value).isNotNull()
    }

    @Test
    fun testFetchTrustbadgeDataWorksWithRepoFetchFailure() = runTest {

        // arrange
        val testDispatcher = StandardTestDispatcher(testScheduler)
        val fakeTrustbadgeRepo = getFakeTrustbadgeRepository(Result.failure(Throwable("failed")))
        val sut = TrustbadgeViewModel(
            scope = this,
            dispatcherIO = testDispatcher,
            dispatcherMain = testDispatcher,
            trustbadgeRepo = fakeTrustbadgeRepo,
        )

        // act
        sut.fetchTrustbadgeData("fakeString", "fakeString")
        advanceUntilIdle()

        // assert
        assertThat(sut.trustbadgeData.value).isNull()
    }
}