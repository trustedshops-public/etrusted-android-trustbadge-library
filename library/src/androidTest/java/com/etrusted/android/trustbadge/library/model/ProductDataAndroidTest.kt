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

class ProductDataAndroidTest {

    @Test
    fun testFromStringReturnsCorrectProductData() {
        // arrange
        val goodData = ServerResponses.ProductDataGoodResponse.content

        // act
        val productData = ProductData.fromString(goodData)

        // assert
        assertThat(productData.id).isEqualTo("prt-fake")
        assertThat(productData.name).isEqualTo("fake name")
        assertThat(productData.url).isEqualTo("fake url")
        assertThat(productData.channelId).isEqualTo("fake channel")
        assertThat(productData.accountId).isEqualTo("fake account")
        assertThat(productData.sku).isEqualTo("fake sku")
        assertThat(productData.gtin).isEqualTo("fake gtin")
        assertThat(productData.mpn).isEqualTo("fake mpn")
        assertThat(productData.brand).isEqualTo("fake brand")
    }

    @Test
    fun testFromStringReturnsCorrectYearlyAggregateRatingPeriod() {
        // arrange
        val goodData = ServerResponses.ProductDataGoodResponse.content

        // act
        val productData = ProductData.fromString(goodData)

        // assert
        assertThat(productData.image.hubPage.url).isNotNull()
        assertThat(productData.image.hubPage.width).isNotNull()
        assertThat(productData.image.hubPage.height).isNotNull()
        assertThat(productData.image.questionnaire.url).isNotNull()
        assertThat(productData.image.questionnaire.width).isNotNull()
        assertThat(productData.image.questionnaire.height).isNotNull()
        assertThat(productData.image.original.url).isNotNull()
        assertThat(productData.image.original.width).isNotNull()
        assertThat(productData.image.original.height).isNotNull()
    }

    @Test
    fun testFromStringThrowsJsonExceptionWithBadData() {

        // arrange
        val badData = ServerResponses.ProductDataBadResponse.content

        // act
        val failure: JSONException =
            Assert.assertThrows(JSONException::class.java) { ProductData.fromString(badData) }

        // assert
        assertThat(failure).isNotNull()
    }
}