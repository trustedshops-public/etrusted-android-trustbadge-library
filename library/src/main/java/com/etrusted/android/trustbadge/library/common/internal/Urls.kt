/*
 * Created by Ali Kabiri on 10.1.2023.
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

package com.etrusted.android.trustbadge.library.common.internal
import com.etrusted.android.trustbadge.library.BuildConfig
import com.etrusted.android.trustbadge.library.common.internal.EnvironmentKey.Companion.forRawValue

internal const val prodChannelsUrl = "https://api.etrusted.com/channels"
internal const val prodAuthUrl = "https://login.etrusted.com/oauth/token"
internal const val prodTrustbadgeDataUrl = "https://cdn1.api.trustedshops.com"
internal const val prodProductDataUrl = "https://integrations.etrusted.com"

internal const val testChannelsUrl = "https://api.etrusted.site/channels"
internal const val testAuthUrl = "https://login-qa.etrusted.com/oauth/token"
internal const val testTrustbadgeDataUrl = "https://cdn1.api-qa.trustedshops.com"
internal const val testProductDataUrl = "https://integrations.etrusted.site"

internal const val devChannelsUrl = "https://api.etrusted.koeln/channels"
internal const val devAuthUrl = "https://login-integr.etrusted.com/oauth/token"
internal const val devTrustbadgeDataUrl = "https://cdn1.api-dev.trustedshops.com"
internal const val devProductDataUrl = "https://integrations.etrusted.koeln"

internal interface IUrls {
    fun authenticationUrl(env: EnvironmentKey = forRawValue(BuildConfig.BUILD_TYPE)): String
    fun trustbadgeJsonUrl(env: EnvironmentKey = forRawValue(BuildConfig.BUILD_TYPE)): String
    fun channelAggregateRatingUrl(env: EnvironmentKey = forRawValue(BuildConfig.BUILD_TYPE)): String
    fun productDataJsonUrl(env: EnvironmentKey = forRawValue(BuildConfig.BUILD_TYPE)): String
}

object Urls: IUrls {

    override fun authenticationUrl(env: EnvironmentKey): String
        = when (env) {
            EnvironmentKey.RELEASE -> prodAuthUrl
            EnvironmentKey.TEST -> testAuthUrl
            EnvironmentKey.DEBUG -> devAuthUrl
            else -> prodAuthUrl
        }

    override fun trustbadgeJsonUrl(env: EnvironmentKey): String
        = when (env) {
            EnvironmentKey.RELEASE -> prodTrustbadgeDataUrl
            EnvironmentKey.TEST -> testTrustbadgeDataUrl
            EnvironmentKey.DEBUG -> devTrustbadgeDataUrl
            else -> prodTrustbadgeDataUrl
        }

    override fun channelAggregateRatingUrl(env: EnvironmentKey): String
        = when (env) {
            EnvironmentKey.RELEASE -> prodChannelsUrl
            EnvironmentKey.TEST -> testChannelsUrl
            EnvironmentKey.DEBUG -> devChannelsUrl
            else -> prodChannelsUrl
        }

    override fun productDataJsonUrl(env: EnvironmentKey): String
        = when (env) {
            EnvironmentKey.RELEASE -> prodProductDataUrl
            EnvironmentKey.TEST -> testProductDataUrl
            EnvironmentKey.DEBUG -> devProductDataUrl
            else -> prodProductDataUrl
        }
}