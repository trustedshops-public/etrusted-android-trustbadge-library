/*
 * Created by Ali Kabiri on 3.1.2023.
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

import org.json.JSONObject
import java.util.Date
import kotlin.math.abs

internal data class AuthenticationToken(
    val accessToken: String,
    val expiresIn: Int,
    val refreshExpiresIn: Int,
    val tokenType: String,
    val scope: String,
    val notBeforePolicy: Int,
    val latestAuthenticationTimestamp: Date,

) {

    internal val isTokenExpired: Boolean
        get() {
            val now = Date()
            // find the time absolute distance between now and latest authentication timestamp
            val timeElapsed = abs(latestAuthenticationTimestamp.time - now.time)
            return timeElapsed >= expiresIn
        }

    internal companion object {
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_EXPIRES_IN = "expires_in"
        private const val KEY_REFRESH_EXPIRES_IN = "refresh_expires_in"
        private const val KEY_TOKEN_TYPE = "token_type"
        private const val KEY_SCOPE = "scope"
        private const val KEY_NOT_BEFORE_POLICY = "not-before-policy"

        internal fun fromJson(body: String): AuthenticationToken {
            val bodyJson = JSONObject(body)

            return AuthenticationToken(
                accessToken = bodyJson.getString(KEY_ACCESS_TOKEN),
                expiresIn = bodyJson.getInt(KEY_EXPIRES_IN),
                refreshExpiresIn = bodyJson.getInt(KEY_REFRESH_EXPIRES_IN),
                tokenType = bodyJson.getString(KEY_TOKEN_TYPE),
                scope = bodyJson.getString(KEY_SCOPE),
                notBeforePolicy = bodyJson.getInt(KEY_NOT_BEFORE_POLICY),
                latestAuthenticationTimestamp = Date(),
            )
        }
    }
}