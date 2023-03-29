/*
 * Created by Ali Kabiri on 18.3.2023.
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

package com.etrusted.android.trustbadge.library.data.repository

import com.etrusted.android.trustbadge.library.common.internal.getFakeAuthToken
import com.etrusted.android.trustbadge.library.common.internal.getFakeChannelInfo
import com.etrusted.android.trustbadge.library.common.internal.getFakeTrustbadgeData
import com.etrusted.android.trustbadge.library.data.datasource.IAuthenticationDatasource
import com.etrusted.android.trustbadge.library.data.datasource.IShopGradeDetailDatasource
import com.etrusted.android.trustbadge.library.data.datasource.ITrustbadgeDatasource
import com.etrusted.android.trustbadge.library.model.AuthenticationToken
import com.etrusted.android.trustbadge.library.model.ChannelInfo
import com.etrusted.android.trustbadge.library.model.TrustbadgeData
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class TrustbadgeRepositoryAndroidTest {

    @Test
    fun testFetchTrustbadgeDataReturnsSuccessfully() = runTest {
        // arrange
        val fakeAuthDatasource = object: IAuthenticationDatasource {
            override suspend fun getAccessTokenUsingSecret(): Result<AuthenticationToken> {
                return Result.success(getFakeAuthToken())
            }
        }
        val fakeTrustbadgeDatasource = object: ITrustbadgeDatasource {
            override suspend fun fetchTrustbadge(tsid: String): Result<TrustbadgeData> {
                return Result.success(getFakeTrustbadgeData())
            }
        }
        val fakeShopGradeDetailDatasource = object: IShopGradeDetailDatasource {
            override suspend fun fetchShopGradeDetail(
                channelId: String,
                accessToken: String
            ): Result<ChannelInfo> {
                return Result.success(getFakeChannelInfo())
            }

        }
        val sut = TrustbadgeRepository(
            auth = fakeAuthDatasource,
            trustbadgeDatasource = fakeTrustbadgeDatasource,
            shopGradeDetailDatasource = fakeShopGradeDetailDatasource,
        )

        // act
        val result = sut.fetchTrustbadgeData("fakeString", "fakeString")

        // assert
        assertThat(result.isSuccess).isTrue()
    }
}