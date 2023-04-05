/*
 * Created by Ali Kabiri on 30.1.2023.
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

package com.etrusted.android.trustbadge.library.model

import com.etrusted.android.trustbadge.library.common.internal.ServerResponses
import com.etrusted.android.trustbadge.library.common.internal.getFakeChannelInfo
import com.etrusted.android.trustbadge.library.common.internal.getFakeTrustbadgeData
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TrustbadgeDataAndroidTest {

    @Test
    fun testFromStringReturnsCorrectTrustbadge() {

        // arrange
        val fakeStringInJsonFile = "fakeString"
        val goodData = ServerResponses.TrustbadgeDataGoodResponse.content

        // act
        val trustbadge = TrustbadgeData.fromString(goodData)

        // assert
        assertThat(trustbadge.shop.tsid).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.name).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.url).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.languageISO2).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.targetMarketISO3).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.trustMark.status).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.trustMark.validFrom).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.trustMark.validTo).isEqualTo(fakeStringInJsonFile)
    }

    @Test
    fun testFromStringReturnsCorrectTrustbadgeWithEmptyValidFromToAttributes() {

        // arrange
        val fakeStringInJsonFile = "fakeString"
        val goodData = ServerResponses.TrustbadgeDataGoodResponse2.content

        // act
        val trustbadge = TrustbadgeData.fromString(goodData)

        // assert
        assertThat(trustbadge.shop.tsid).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.name).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.url).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.languageISO2).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.targetMarketISO3).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.trustMark.status).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.trustMark.validFrom).isEmpty()
        assertThat(trustbadge.shop.trustMark.validTo).isEmpty()
    }

    @Test
    fun testFromStringReturnsCorrectTrustbadgeWithoutValidFromToAttributes() {

        // arrange
        val fakeStringInJsonFile = "fakeString"
        val goodData = ServerResponses.TrustbadgeDataGoodResponse3.content

        // act
        val trustbadge = TrustbadgeData.fromString(goodData)

        // assert
        assertThat(trustbadge.shop.tsid).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.name).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.url).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.languageISO2).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.targetMarketISO3).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.trustMark.status).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.trustMark.validFrom).isEmpty()
        assertThat(trustbadge.shop.trustMark.validTo).isEmpty()
    }

    @Test
    fun testEnrichTrustbadgeDataWithInfoWorks() {
        // arrange
        val fakeRating = 3.57f
        val fakeChannelInfo = getFakeChannelInfo(fakeRating)
        val fakeTrustbadgeData = getFakeTrustbadgeData()

        // act
        val enrichedTrustbadge = fakeTrustbadgeData.enrichWithChannelInfo(fakeChannelInfo)

        // assert
        assertThat(enrichedTrustbadge.shop.rating).isNotNull()
        assertThat(enrichedTrustbadge.shop.rating).isEqualTo(fakeRating)
        assertThat(enrichedTrustbadge.shop.rating).isEqualTo(fakeChannelInfo.year.rating)
    }
}