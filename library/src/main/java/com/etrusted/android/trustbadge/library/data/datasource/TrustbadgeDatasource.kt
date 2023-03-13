/*
 * Created by Ali Kabiri on 31.1.2023.
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

package com.etrusted.android.trustbadge.library.data.datasource

import com.etrusted.android.trustbadge.library.common.internal.IUrls
import com.etrusted.android.trustbadge.library.common.internal.Urls
import com.etrusted.android.trustbadge.library.common.internal.readStream
import com.etrusted.android.trustbadge.library.model.TrustbadgeData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

@Suppress("BlockingMethodInNonBlockingContext")
internal class TrustbadgeDatasource(
    private val urls: IUrls = Urls,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    internal suspend fun fetchTrustbadge(
        tsid: String,
    ): Result<TrustbadgeData> {

        return withContext(dispatcher) {

            val url = URL(
                urls.trustbadgeJsonUrl() +
                    "/shops/$tsid/mobiles/v1/sdks/ios/trustmarks.json")
            val urlConnection = url.openConnection() as HttpURLConnection

            try {

                val inputStream = BufferedInputStream(urlConnection.inputStream)
                val body = readStream(inputStream)
                val tBadgeData = TrustbadgeData.fromString(body)
                Result.success(tBadgeData)

            } catch (e: Exception) {

                Result.failure(e)

            } finally {

                urlConnection.disconnect()
            }
        }
    }
}