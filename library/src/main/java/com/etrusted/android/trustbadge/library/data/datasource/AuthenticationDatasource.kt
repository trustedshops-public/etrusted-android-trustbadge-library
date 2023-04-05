/*
 * Created by Ali Kabiri on 30.1.2023.
 * Copyright (c) 2022-2023 Trusted Shops AG
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

import com.etrusted.android.trustbadge.library.ILibrary
import com.etrusted.android.trustbadge.library.TrustbadgeLibrary
import com.etrusted.android.trustbadge.library.common.internal.IUrls
import com.etrusted.android.trustbadge.library.common.internal.Urls
import com.etrusted.android.trustbadge.library.common.internal.readStream
import com.etrusted.android.trustbadge.library.common.internal.setPostParams
import com.etrusted.android.trustbadge.library.model.AuthenticationToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

internal interface IAuthenticationDatasource {
    suspend fun getAccessTokenUsingSecret(): Result<AuthenticationToken>
}

@Suppress("BlockingMethodInNonBlockingContext")
internal class AuthenticationDatasource(
    private val library: ILibrary = TrustbadgeLibrary,
    private val urls: IUrls = Urls,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
): IAuthenticationDatasource {

    override suspend fun getAccessTokenUsingSecret(): Result<AuthenticationToken> {

        return withContext(dispatcher) {

            val url = URL(urls.authenticationUrl())
            val urlConnection = url.openConnection() as HttpsURLConnection
            urlConnection.requestMethod = "POST"

            val params: MutableList<Pair<String, String>> = mutableListOf()
            params.add(Pair("Content-Type", "application/x-www-form-urlencoded"))
            params.add(Pair("grant_type", "client_credentials"))
            params.add(Pair("client_id", library.config.clientId))
            params.add(Pair("client_secret", library.config.clientSecret))
            params.add(Pair("audience", "https://api.etrusted.com"))

            urlConnection.setPostParams(params)

            urlConnection.connect()

            try {

                val inputStream = BufferedInputStream(urlConnection.inputStream)
                val body = readStream(inputStream)
                val authTokenData = AuthenticationToken.fromJson(body)
                Result.success(authTokenData)

            } catch (e: Exception) {

                val errorStream = BufferedInputStream(urlConnection.errorStream)
                val errorBody =
                    try { readStream(errorStream) }
                    catch (e: Exception) { return@withContext Result.failure(e) }
                    finally { urlConnection.disconnect() }

                if (errorBody.isNotBlank()) { Result.failure(Error(errorBody)) }
                else { Result.failure(e) }

            } finally { urlConnection.disconnect() }
        }
    }
}