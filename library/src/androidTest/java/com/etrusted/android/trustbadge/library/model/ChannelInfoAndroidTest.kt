/*
 * Created by Ali Kabiri on 30.1.2023.
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

package com.etrusted.android.trustbadge.library.model

import com.etrusted.android.trustbadge.library.common.internal.ServerResponses
import com.etrusted.android.trustbadge.library.common.internal.getFakeTrustbadgeData
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ChannelInfoAndroidTest {

    @Test
    fun testFromStringReturnsCorrectChannelInfo() {
        // arrange
        val goodData = ServerResponses.ChannelInfoGoodResponse.content

        // act
        val channelInfo = ChannelInfo.fromString(goodData)

        // assert
        assertThat(channelInfo.week.count).isEqualTo(0)
        assertThat(channelInfo.week.rating).isEqualTo(0f)
        assertThat(channelInfo.week.period).isEqualTo(null)
        assertThat(channelInfo.week.distribution).isEqualTo(null)

        assertThat(channelInfo.month.count).isEqualTo(0)
        assertThat(channelInfo.month.rating).isEqualTo(0f)
        assertThat(channelInfo.month.period).isEqualTo(null)
        assertThat(channelInfo.month.distribution).isEqualTo(null)

        assertThat(channelInfo.quarter.count).isEqualTo(2)
        assertThat(channelInfo.quarter.rating).isEqualTo(3.5f)
        assertThat(channelInfo.quarter.period).isEqualTo(null)
        assertThat(channelInfo.quarter.distribution).isEqualTo(null)

        assertThat(channelInfo.year.count).isEqualTo(5)
        assertThat(channelInfo.year.rating).isEqualTo(4.4f)
        assertThat(channelInfo.year.period).isEqualTo(null)
        assertThat(channelInfo.year.distribution).isEqualTo(null)

        assertThat(channelInfo.overall.count).isEqualTo(70)
        assertThat(channelInfo.overall.rating).isEqualTo(3.51f)
        assertThat(channelInfo.overall.period).isEqualTo(null)
        assertThat(channelInfo.overall.distribution).isEqualTo(null)
    }

    @Test
    fun testEnrichTrustbadgeDataWithInfoWorks() {
        // arrange
        val sut = ChannelInfo(
            week = ChannelInfo.AggregateRating(count = 1, rating = 1.5f),
            month = ChannelInfo.AggregateRating(count = 2, rating = 2.5f),
            quarter = ChannelInfo.AggregateRating(count = 3, rating = 3.5f),
            year = ChannelInfo.AggregateRating(count = 4, rating = 4.5f),
            overall = ChannelInfo.AggregateRating(count = 5, rating = 4.75f),
        )
        val fakeTrustbadgeData = getFakeTrustbadgeData()

        // act
        val enrichedTrustbadge = sut.enrichTrustbadgeDataWithInfo(fakeTrustbadgeData)

        // assert
        assertThat(enrichedTrustbadge.shop.rating).isNotNull()
        assertThat(enrichedTrustbadge.shop.rating).isEqualTo(sut.year.rating)
    }
}