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
import java.util.*

class AuthenticationTokenAndroidTest {

    @Test
    fun testFromStringReturnsCorrectAuthenticationToken() {
        // arrange
        val goodData = ServerResponses.AuthenticationTokenGoodResponse.content

        // act
        val authenticationToken = AuthenticationToken.fromJson(goodData)

        // assert
        assertThat(authenticationToken.accessToken).isEqualTo("eyJBla.eyJBla.eMJBla")
        assertThat(authenticationToken.expiresIn).isEqualTo(3600)
        assertThat(authenticationToken.refreshExpiresIn).isEqualTo(0)
        assertThat(authenticationToken.tokenType).isEqualTo("Bearer")
        assertThat(authenticationToken.scope).isEqualTo("email profile")
        assertThat(authenticationToken.notBeforePolicy).isEqualTo(1651869373)
    }

    @Test
    fun testIsTokenExpiredReturnsTrue() {
        // arrange
        val nowMillis = Date().time
        val lastAuthenticationMillis = nowMillis - (300*60*1000) // last auth was 300 minutes ago
        val pastDate = Date().apply { time = lastAuthenticationMillis }
        val fakeExpirationMillis = 30*60*1000 // token valid for 30 minutes
        val goodData = ServerResponses.AuthenticationTokenGoodResponse.content
        val sut = AuthenticationToken.fromJson(goodData).copy(
            expiresIn = fakeExpirationMillis,
            latestAuthenticationTimestamp = pastDate,
        )

        // act
        val result = sut.isTokenExpired

        // assert
        assertThat(result).isTrue()
    }

    @Test
    fun testIsTokenExpiredReturnsFalse() {
        // arrange
        val nowMillis = Date().time
        val lastAuthenticationMillis = nowMillis + (20*60*1000) // last auth was 20 minutes ago
        val lastAuthenticationDate = Date().apply { time = lastAuthenticationMillis }
        val fakeExpirationMillis = 30*60*1000 // token valid for 30 minutes
        val goodData = ServerResponses.AuthenticationTokenGoodResponse.content
        val sut = AuthenticationToken.fromJson(goodData).copy(
            expiresIn = fakeExpirationMillis,
            latestAuthenticationTimestamp = lastAuthenticationDate,
        )

        // act
        val result = sut.isTokenExpired

        // assert
        assertThat(result).isFalse()
    }
}