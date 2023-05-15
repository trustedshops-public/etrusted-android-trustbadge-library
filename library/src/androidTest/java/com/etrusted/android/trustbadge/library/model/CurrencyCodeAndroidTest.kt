/*
 * Created on 11.05.2023.
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

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CurrencyCodeAndroidTest {

    @Test
    fun testForRawReturnsCorrectSymbolEUR() {
        // arrange
        val goodData = "EUR"
        val expectedSymbol = "€"

        // act
        val currencyCode = CurrencyCode.forRaw(goodData)

        // assert
        assertThat(currencyCode).isNotNull()
        assertThat(currencyCode?.symbol).isEqualTo(expectedSymbol)
    }

    @Test
    fun testForRawReturnsCorrectSymbolCHF() {
        // arrange
        val goodData = "CHF"
        val expectedSymbol = "CHF"

        // act
        val currencyCode = CurrencyCode.forRaw(goodData)

        // assert
        assertThat(currencyCode).isNotNull()
        assertThat(currencyCode?.symbol).isEqualTo(expectedSymbol)
    }

    @Test
    fun testForRawReturnsCorrectSymbolGBP() {
        // arrange
        val goodData = "GBP"
        val expectedSymbol = "£"

        // act
        val currencyCode = CurrencyCode.forRaw(goodData)

        // assert
        assertThat(currencyCode).isNotNull()
        assertThat(currencyCode?.symbol).isEqualTo(expectedSymbol)
    }

    @Test
    fun testForRawReturnsCorrectSymbolPLN() {
        // arrange
        val goodData = "PLN"
        val expectedSymbol = "zł"

        // act
        val currencyCode = CurrencyCode.forRaw(goodData)

        // assert
        assertThat(currencyCode).isNotNull()
        assertThat(currencyCode?.symbol).isEqualTo(expectedSymbol)
    }

    @Test
    fun testForRawReturnsCorrectSymbolNOK() {
        // arrange
        val goodData = "NOK"
        val expectedSymbol = "kr"

        // act
        val currencyCode = CurrencyCode.forRaw(goodData)

        // assert
        assertThat(currencyCode).isNotNull()
        assertThat(currencyCode?.symbol).isEqualTo(expectedSymbol)
    }

    @Test
    fun testForRawReturnsCorrectSymbolSEK() {
        // arrange
        val goodData = "SEK"
        val expectedSymbol = "kr"

        // act
        val currencyCode = CurrencyCode.forRaw(goodData)

        // assert
        assertThat(currencyCode).isNotNull()
        assertThat(currencyCode?.symbol).isEqualTo(expectedSymbol)
    }

    @Test
    fun testForRawReturnsCorrectSymbolDKK() {
        // arrange
        val goodData = "DKK"
        val expectedSymbol = "kr"

        // act
        val currencyCode = CurrencyCode.forRaw(goodData)

        // assert
        assertThat(currencyCode).isNotNull()
        assertThat(currencyCode?.symbol).isEqualTo(expectedSymbol)
    }

    @Test
    fun testForRawReturnsCorrectSymbolRON() {
        // arrange
        val goodData = "RON"
        val expectedSymbol = "L"

        // act
        val currencyCode = CurrencyCode.forRaw(goodData)

        // assert
        assertThat(currencyCode).isNotNull()
        assertThat(currencyCode?.symbol).isEqualTo(expectedSymbol)
    }

    @Test
    fun testForRawReturnsCorrectSymbolCZK() {
        // arrange
        val goodData = "CZK"
        val expectedSymbol = "Kč"

        // act
        val currencyCode = CurrencyCode.forRaw(goodData)

        // assert
        assertThat(currencyCode).isNotNull()
        assertThat(currencyCode?.symbol).isEqualTo(expectedSymbol)
    }

    @Test
    fun testForRawReturnsNullWithBadData() {

        // arrange
        val badData = ""

        // act
        val currencyCode = CurrencyCode.forRaw(badData)

        // assert
        assertThat(currencyCode).isNull()
    }
}