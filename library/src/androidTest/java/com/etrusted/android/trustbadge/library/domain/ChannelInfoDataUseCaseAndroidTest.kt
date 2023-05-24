/*
 * Created by Ali Kabiri on 27.3.2023.
 * Copyright (c) 2023 Trusted Shops AG
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

package com.etrusted.android.trustbadge.library.domain

import com.etrusted.android.trustbadge.library.common.internal.getFakeChannelInfoRepository
import com.etrusted.android.trustbadge.library.domain.GetTrustbadgeDataUseCase.*
import com.etrusted.android.trustbadge.library.model.ChannelInfo
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ChannelInfoDataUseCaseAndroidTest {

    @Test
    fun testChannelInfoDataUseCaseReturnsSuccessfully() = runTest {

        // arrange
        val fakeChannelInfoRepository = getFakeChannelInfoRepository()
        val sut = GetChannelInfoDataUseCase(
            channelInfoRepository = fakeChannelInfoRepository
        )

        // act
        val result = sut(channelId = "fakeString")
        advanceUntilIdle()

        // assert
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).apply {
            isNotNull()
            isInstanceOf(ChannelInfo::class.java)
        }
    }

    @Test
    fun testChannelInfoDataUseCaseFailsCorrectly() = runTest {

        // arrange
        val fakeMsg = "failed"
        val fakeChannelInfoRepository = getFakeChannelInfoRepository(
            result = Result.failure(Throwable(fakeMsg))
        )
        val sut = GetChannelInfoDataUseCase(
            channelInfoRepository = fakeChannelInfoRepository
        )

        // act
        val result = sut(channelId = "fakeString")
        advanceUntilIdle()

        // assert
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isNotNull()
        assertThat(result.exceptionOrNull()?.message).isEqualTo(fakeMsg)
    }

}