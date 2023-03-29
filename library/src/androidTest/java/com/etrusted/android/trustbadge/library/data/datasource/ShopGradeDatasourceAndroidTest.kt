/*
 * Created by Ali Kabiri on 13.3.2023.
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

package com.etrusted.android.trustbadge.library.data.datasource

import com.etrusted.android.trustbadge.library.common.internal.ServerResponses
import com.etrusted.android.trustbadge.library.common.internal.getUrlsFor
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ShopGradeDatasourceAndroidTest {

    @Test
    fun testFetchShopGradeDetailReturnsSuccessfully() = runTest {

        // arrange
        val goodData = ServerResponses.ChannelInfoGoodResponse.content
        val server = MockWebServer()
        server.enqueue(MockResponse().apply { setBody(goodData) })
        server.start()
        val mockUrl = server.url("")
        val mockUrlRoot = "http://${mockUrl.host}:${mockUrl.port}/"
        val mockUrls = getUrlsFor(mockUrlRoot)
        val sut = ShopGradeDetailDatasource(urls = mockUrls)

        // act
        val result = sut.fetchShopGradeDetail("fakeChannelId", "fakeAccessToken")

        // assert
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun testFetchShopGradeDetailFailsCorrectly() = runTest {

        // arrange
        val badData = ServerResponses.ChannelInfoBadResponse.content
        val server = MockWebServer()
        server.enqueue(MockResponse().apply { setBody(badData) })
        server.start()
        val mockUrl = server.url("")
        val mockUrlRoot = "http://${mockUrl.host}:${mockUrl.port}/"
        val mockUrls = getUrlsFor(mockUrlRoot)
        val sut = ShopGradeDetailDatasource(urls = mockUrls)

        // act
        val result = sut.fetchShopGradeDetail("fakeChannelId", "fakeAccessToken")

        // assert
        assertThat(result.isFailure).isTrue()
    }
}