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

package com.etrusted.android.trustbadge.library.common.internal

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import javax.net.ssl.HttpsURLConnection

internal fun HttpsURLConnection.setPostParams(params: List<Pair<String, String>>) {
    val os = this.outputStream
    BufferedWriter(OutputStreamWriter(os, "UTF-8")).use {
        it.write(getQuery(params))
    }
    os.close()
}

@Throws(UnsupportedEncodingException::class)
private fun getQuery(params: List<Pair<String, String>>): String {
    val result = StringBuilder()
    var first = true
    for (pair in params) {
        if (first) first = false else result.append("&")
        result.append(URLEncoder.encode(pair.first, "UTF-8"))
        result.append("=")
        result.append(URLEncoder.encode(pair.second, "UTF-8"))
    }
    return result.toString()
}