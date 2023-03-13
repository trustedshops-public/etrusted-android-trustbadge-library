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

import android.content.Context
import com.etrusted.android.trustbadge.library.ILibrary
import com.etrusted.android.trustbadge.library.model.TrustbadgeConfig

internal fun getUrlsFor(endpoint: String): IUrls {
    return object: IUrls {
        override fun authenticationUrl(env: EnvironmentKey): String = endpoint
        override fun trustbadgeJsonUrl(env: EnvironmentKey): String = endpoint
        override fun channelAggregateRatingUrl(env: EnvironmentKey): String = endpoint
    }
}

internal fun getFakeLibrary(): ILibrary {
    return object: ILibrary {
        override var config = TrustbadgeConfig("fakeId", "fakeSecret")
        override fun configure(context: Context): ILibrary { return this }
    }
}