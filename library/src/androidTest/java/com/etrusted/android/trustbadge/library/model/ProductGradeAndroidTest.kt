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
import com.google.common.truth.Truth.assertThat
import org.json.JSONException
import org.junit.Assert
import org.junit.Test

class ProductGradeAndroidTest {

    @Test
    fun testFromStringReturnsCorrectProductGrade() {
        // arrange
        val goodData = ServerResponses.ProductGradeGoodResponse.content

        // act
        val productGrade = ProductGrade.fromString(goodData)

        // assert
        assertThat(productGrade.year.count).isEqualTo(4)
        assertThat(productGrade.year.rating).isEqualTo(5f)
    }

    @Test
    fun testFromStringReturnsCorrectYearlyAggregateRatingPeriod() {
        // arrange
        val goodData = ServerResponses.ProductGradeGoodResponse.content

        // act
        val productGrade = ProductGrade.fromString(goodData)

        // assert
        assertThat(productGrade.year.period?.start).isNotNull()
        assertThat(productGrade.year.period?.end).isNotNull()
        assertThat(productGrade.year.period).isNotNull()
        assertThat(productGrade.year.period?.firstConsideredReviewSubmission).isNotNull()
        assertThat(productGrade.year.period?.lastConsideredReviewSubmission).isNotNull()
        assertThat(productGrade.year.period?.calculatedAt).isNotNull()
        assertThat(productGrade.year.period?.ratingTrend).isNotNull()

        assertThat(productGrade.year.distribution).isNotNull()
        assertThat(productGrade.year.distribution?.oneStar).isNotNull()
        assertThat(productGrade.year.distribution?.twoStars).isNotNull()
        assertThat(productGrade.year.distribution?.threeStars).isNotNull()
        assertThat(productGrade.year.distribution?.fourStars).isNotNull()
        assertThat(productGrade.year.distribution?.fiveStars).isNotNull()
    }

    @Test
    fun testFromStringThrowsJsonExceptionWithBadData() {

        // arrange
        val badData = ServerResponses.ProductGradeBadResponse.content

        // act
        val failure: JSONException =
            Assert.assertThrows(JSONException::class.java) { ProductGrade.fromString(badData) }

        // assert
        assertThat(failure).isNotNull()
    }
}