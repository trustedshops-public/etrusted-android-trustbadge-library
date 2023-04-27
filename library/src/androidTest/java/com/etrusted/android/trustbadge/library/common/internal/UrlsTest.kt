/*
 * Created by Ali Kabiri on 10.3.2023.
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

package com.etrusted.android.trustbadge.library.common.internal

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UrlsTest {

    @Test
    fun testAuthenticationUrlReturnsCorrectRawValueForEachEnvironment() {

        val urlReleaseRaw = Urls.authenticationUrl(env = EnvironmentKey.RELEASE)
        val urlDebugRaw = Urls.authenticationUrl(env = EnvironmentKey.DEBUG)
        val urlUnknownRaw = Urls.authenticationUrl(env = EnvironmentKey.UNKNOWN)

        assertThat(urlReleaseRaw).isEqualTo(prodAuthUrl)
        assertThat(urlDebugRaw).isEqualTo(devAuthUrl)
        assertThat(urlUnknownRaw).isEqualTo(prodAuthUrl)
    }

    @Test
    fun testTrustbadgeJsonUrlReturnsCorrectRawValueForEachEnvironment() {

        val urlReleaseRaw = Urls.trustbadgeJsonUrl(env = EnvironmentKey.RELEASE)
        val urlDebugRaw = Urls.trustbadgeJsonUrl(env = EnvironmentKey.DEBUG)
        val urlUnknownRaw = Urls.trustbadgeJsonUrl(env = EnvironmentKey.UNKNOWN)

        assertThat(urlReleaseRaw).isEqualTo(prodTrustbadgeDataUrl)
        assertThat(urlDebugRaw).isEqualTo(devTrustbadgeDataUrl)
        assertThat(urlUnknownRaw).isEqualTo(prodTrustbadgeDataUrl)
    }

    @Test
    fun testChannelAggregateRatingUrlReturnsCorrectRawValueForEachEnvironment() {

        val urlReleaseRaw = Urls.channelAggregateRatingUrl(env = EnvironmentKey.RELEASE)
        val urlDebugRaw = Urls.channelAggregateRatingUrl(env = EnvironmentKey.DEBUG)
        val urlUnknownRaw = Urls.channelAggregateRatingUrl(env = EnvironmentKey.UNKNOWN)

        assertThat(urlReleaseRaw).isEqualTo(prodChannelsUrl)
        assertThat(urlDebugRaw).isEqualTo(devChannelsUrl)
        assertThat(urlUnknownRaw).isEqualTo(prodChannelsUrl)
    }

    @Test
    fun testProductGradeJsonUrlReturnsCorrectRawValueForEachEnvironment() {

        val urlReleaseRaw = Urls.productDataJsonUrl(env = EnvironmentKey.RELEASE)
        val urlDebugRaw = Urls.productDataJsonUrl(env = EnvironmentKey.DEBUG)
        val urlUnknownRaw = Urls.productDataJsonUrl(env = EnvironmentKey.UNKNOWN)

        assertThat(urlReleaseRaw).isEqualTo(prodProductDataUrl)
        assertThat(urlDebugRaw).isEqualTo(devProductDataUrl)
        assertThat(urlUnknownRaw).isEqualTo(prodProductDataUrl)
    }
}