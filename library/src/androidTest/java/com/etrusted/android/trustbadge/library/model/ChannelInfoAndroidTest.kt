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
import com.etrusted.android.trustbadge.library.model.ChannelInfo.AggregateRating.AggregateRatingPeriod.RatingTrend
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

        assertThat(channelInfo.month.count).isEqualTo(0)
        assertThat(channelInfo.month.rating).isEqualTo(0f)

        assertThat(channelInfo.quarter.count).isEqualTo(2)
        assertThat(channelInfo.quarter.rating).isEqualTo(3.5f)

        assertThat(channelInfo.year.count).isEqualTo(5)
        assertThat(channelInfo.year.rating).isEqualTo(4.4f)

        assertThat(channelInfo.overall.count).isEqualTo(70)
        assertThat(channelInfo.overall.rating).isEqualTo(3.51f)
    }

    @Test
    fun testFromStringReturnsCorrectWeeklyAggregateRatingPeriod() {
        // arrange
        val goodData = ServerResponses.ChannelInfoGoodResponse.content

        // act
        val channelInfo = ChannelInfo.fromString(goodData)

        // assert
        assertThat(channelInfo.week.period).isNotNull()
        assertThat(channelInfo.week.period?.start).isNotNull()
        assertThat(channelInfo.week.period?.end).isNotNull()
        assertThat(channelInfo.week.period?.firstConsideredReviewSubmission).isNull()
        assertThat(channelInfo.week.period?.lastConsideredReviewSubmission).isNull()
        assertThat(channelInfo.week.period?.calculatedAt).isNotNull()
        assertThat(channelInfo.week.period?.ratingTrend).isNull()

        assertThat(channelInfo.week.distribution).isNotNull()
        assertThat(channelInfo.week.distribution?.oneStar).isNotNull()
        assertThat(channelInfo.week.distribution?.twoStars).isNotNull()
        assertThat(channelInfo.week.distribution?.threeStars).isNotNull()
        assertThat(channelInfo.week.distribution?.fourStars).isNotNull()
        assertThat(channelInfo.week.distribution?.fiveStars).isNotNull()
    }

    @Test
    fun testFromStringReturnsCorrectMonthlyAggregateRatingPeriod() {
        // arrange
        val goodData = ServerResponses.ChannelInfoGoodResponse.content

        // act
        val channelInfo = ChannelInfo.fromString(goodData)

        // assert
        assertThat(channelInfo.month.period).isNotNull()
        assertThat(channelInfo.month.period?.start).isNotNull()
        assertThat(channelInfo.month.period?.end).isNotNull()
        assertThat(channelInfo.month.period?.firstConsideredReviewSubmission).isNull()
        assertThat(channelInfo.month.period?.lastConsideredReviewSubmission).isNull()
        assertThat(channelInfo.month.period?.calculatedAt).isNotNull()
        assertThat(channelInfo.month.period?.ratingTrend).isNull()

        assertThat(channelInfo.month.distribution).isNotNull()
        assertThat(channelInfo.month.distribution?.oneStar).isNotNull()
        assertThat(channelInfo.month.distribution?.twoStars).isNotNull()
        assertThat(channelInfo.month.distribution?.threeStars).isNotNull()
        assertThat(channelInfo.month.distribution?.fourStars).isNotNull()
        assertThat(channelInfo.month.distribution?.fiveStars).isNotNull()
    }

    @Test
    fun testFromStringReturnsCorrectQuarterlyAggregateRatingPeriod() {
        // arrange
        val goodData = ServerResponses.ChannelInfoGoodResponse.content

        // act
        val channelInfo = ChannelInfo.fromString(goodData)

        // assert
        assertThat(channelInfo.quarter.period).isNotNull()
        assertThat(channelInfo.quarter.period?.start).isNotNull()
        assertThat(channelInfo.quarter.period?.end).isNotNull()
        assertThat(channelInfo.quarter.period?.firstConsideredReviewSubmission).isNotNull()
        assertThat(channelInfo.quarter.period?.lastConsideredReviewSubmission).isNotNull()
        assertThat(channelInfo.quarter.period?.calculatedAt).isNotNull()
        assertThat(channelInfo.quarter.period?.ratingTrend).isEqualTo(RatingTrend.NEUTRAL)

        assertThat(channelInfo.quarter.distribution).isNotNull()
        assertThat(channelInfo.quarter.distribution?.oneStar).isNotNull()
        assertThat(channelInfo.quarter.distribution?.twoStars).isNotNull()
        assertThat(channelInfo.quarter.distribution?.threeStars).isNotNull()
        assertThat(channelInfo.quarter.distribution?.fourStars).isNotNull()
        assertThat(channelInfo.quarter.distribution?.fiveStars).isNotNull()
    }

    @Test
    fun testFromStringReturnsCorrectYearlyAggregateRatingPeriod() {
        // arrange
        val goodData = ServerResponses.ChannelInfoGoodResponse.content

        // act
        val channelInfo = ChannelInfo.fromString(goodData)

        // assert
        assertThat(channelInfo.year.period).isNotNull()
        assertThat(channelInfo.year.period?.start).isNotNull()
        assertThat(channelInfo.year.period?.end).isNotNull()
        assertThat(channelInfo.year.period?.firstConsideredReviewSubmission).isNotNull()
        assertThat(channelInfo.year.period?.lastConsideredReviewSubmission).isNotNull()
        assertThat(channelInfo.year.period?.calculatedAt).isNotNull()
        assertThat(channelInfo.year.period?.ratingTrend).isEqualTo(RatingTrend.POSITIVE)

        assertThat(channelInfo.year.distribution).isNotNull()
        assertThat(channelInfo.year.distribution?.oneStar).isNotNull()
        assertThat(channelInfo.year.distribution?.twoStars).isNotNull()
        assertThat(channelInfo.year.distribution?.threeStars).isNotNull()
        assertThat(channelInfo.year.distribution?.fourStars).isNotNull()
        assertThat(channelInfo.year.distribution?.fiveStars).isNotNull()
    }

    @Test
    fun testFromStringReturnsCorrectOverallAggregateRating() {
        // arrange
        val goodData = ServerResponses.ChannelInfoGoodResponse.content

        // act
        val channelInfo = ChannelInfo.fromString(goodData)

        // assert
        assertThat(channelInfo.overall.period).isNotNull()
        assertThat(channelInfo.overall.period?.start).isNotNull()
        assertThat(channelInfo.overall.period?.end).isNotNull()
        assertThat(channelInfo.overall.period?.firstConsideredReviewSubmission).isNotNull()
        assertThat(channelInfo.overall.period?.lastConsideredReviewSubmission).isNotNull()
        assertThat(channelInfo.overall.period?.calculatedAt).isNotNull()
        assertThat(channelInfo.overall.period?.ratingTrend).isNull()

        assertThat(channelInfo.overall.distribution).isNotNull()
        assertThat(channelInfo.overall.distribution?.oneStar).isNotNull()
        assertThat(channelInfo.overall.distribution?.twoStars).isNotNull()
        assertThat(channelInfo.overall.distribution?.threeStars).isNotNull()
        assertThat(channelInfo.overall.distribution?.fourStars).isNotNull()
        assertThat(channelInfo.overall.distribution?.fiveStars).isNotNull()
    }
}