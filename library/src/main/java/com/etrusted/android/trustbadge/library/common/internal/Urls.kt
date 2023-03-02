/*
 * Created by Ali Kabiri on 10.1.2023.
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

package com.etrusted.android.trustbadge.library.common.internal

import com.etrusted.android.trustbadge.library.BuildConfig

private const val prodChannelsUrl = "https://api.etrusted.com/channels"
private const val prodAuthUrl = "https://login.etrusted.com/oauth/token"
private const val prodTrustbadgeDataUrl = "https://cdn1.api.trustedshops.com"

private const val devTrustbadgeDataUrl = "https://cdn1.api-qa.trustedshops.com"

internal class Urls {
    companion object {
        internal val authenticationUrl: String
            get() = when (EnvironmentKey.forRawValue(BuildConfig.BUILD_TYPE)) {
                EnvironmentKey.RELEASE -> prodAuthUrl
                EnvironmentKey.DEBUG -> prodAuthUrl
                else -> prodAuthUrl
            }
        internal val trustbadgeJsonUrl: String
            get() = when (EnvironmentKey.forRawValue(BuildConfig.BUILD_TYPE)) {
                EnvironmentKey.RELEASE -> prodTrustbadgeDataUrl
                EnvironmentKey.DEBUG -> devTrustbadgeDataUrl
                else -> prodTrustbadgeDataUrl
            }
        internal val channelAggregateRatingUrl: String
            get() = when (EnvironmentKey.forRawValue(BuildConfig.BUILD_TYPE)) {
                EnvironmentKey.RELEASE -> prodChannelsUrl
                EnvironmentKey.DEBUG -> prodChannelsUrl
                else -> prodChannelsUrl
            }
    }
}