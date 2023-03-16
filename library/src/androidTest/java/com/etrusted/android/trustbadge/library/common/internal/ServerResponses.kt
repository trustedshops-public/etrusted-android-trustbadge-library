/*
 * Created by Ali Kabiri on 13.3.2023.
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

import androidx.test.platform.app.InstrumentationRegistry

internal sealed class ServerResponses {
    abstract val path: String
    abstract val content: String

    object ChannelInfoGoodResponse: ServerResponses() {
        override val path: String
            get() = "api.etrusted.com" +
                "/channels/channelid/service-reviews/aggregate-rating/good_response.json"
        override val content: String
            get() = InstrumentationRegistry.getInstrumentation().context.readJsonFile(path)
    }

    object AuthenticationTokenGoodResponse: ServerResponses() {
        override val path: String
            get() = "login.etrusted.com/oauth/token/good_response.json"
        override val content: String
            get() = InstrumentationRegistry.getInstrumentation().context.readJsonFile(path)
    }

    object TrustbadgeDataGoodResponse: ServerResponses() {
        override val path: String
            get() = "cdn1.api.trustedshops.com/trustmark_good_response.json"
        override val content: String
            get() = InstrumentationRegistry.getInstrumentation().context.readJsonFile(path)
    }

    object TrustbadgeDataGoodResponse2: ServerResponses() {
        override val path: String
            get() = "cdn1.api.trustedshops.com/trustmark_good_response_2.json"
        override val content: String
            get() = InstrumentationRegistry.getInstrumentation().context.readJsonFile(path)
    }

    object TrustbadgeDataGoodResponse3: ServerResponses() {
        override val path: String
            get() = "cdn1.api.trustedshops.com/trustmark_good_response_3.json"
        override val content: String
            get() = InstrumentationRegistry.getInstrumentation().context.readJsonFile(path)
    }
}