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
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TrustbadgeDataGuaranteeAndroidTest {

    @Test
    fun testFromStringReturnsCorrectGuarantee() {

        // arrange
        val fakeStringInJsonFile = "fakeString"
        val goodData = ServerResponses.TrustbadgeDataGoodResponse.content

        // act
        val trustbadge = TrustbadgeData.fromString(goodData)

        // assert
        assertThat(trustbadge.shop.guarantee).isNotNull()
        assertThat(trustbadge.shop.guarantee.mainProtectionCurrency).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.guarantee.maxProtectionAmount).isEqualTo(fakeStringInJsonFile)
        assertThat(trustbadge.shop.guarantee.maxProtectionDuration).isEqualTo(fakeStringInJsonFile)
    }

    @Test
    fun testFromStringReturnsCorrectTrustbadgeWithoutGuaranteeAttributes() {

        // arrange
        val goodData = ServerResponses.TrustbadgeDataGoodResponse2.content

        // act
        val trustbadge = TrustbadgeData.fromString(goodData)

        // assert
        assertThat(trustbadge.shop.guarantee).isNotNull()
        assertThat(trustbadge.shop.guarantee.mainProtectionCurrency).isEqualTo("")
        assertThat(trustbadge.shop.guarantee.maxProtectionAmount).isEqualTo("0")
        assertThat(trustbadge.shop.guarantee.maxProtectionDuration).isEqualTo("0")
    }

    @Test
    fun testFromStringReturnsCorrectTrustbadgeWithoutGuaranteeObject() {

        // arrange
        val goodData = ServerResponses.TrustbadgeDataGoodResponse3.content

        // act
        val trustbadge = TrustbadgeData.fromString(goodData)

        // assert
        assertThat(trustbadge.shop.guarantee).isNotNull()
        assertThat(trustbadge.shop.guarantee.mainProtectionCurrency).isEqualTo("")
        assertThat(trustbadge.shop.guarantee.maxProtectionAmount).isEqualTo("0")
        assertThat(trustbadge.shop.guarantee.maxProtectionDuration).isEqualTo("0")
    }
}