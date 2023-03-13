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

package com.etrusted.android.trustbadge.library

import android.content.Context
import com.etrusted.android.trustbadge.library.model.TrustbadgeConfig

internal interface ILibrary {
    var config: TrustbadgeConfig
    fun configure(context: Context): ILibrary
}
object TrustbadgeLibrary: ILibrary {

    override lateinit var config: TrustbadgeConfig

    @Throws
    override fun configure(context: Context): TrustbadgeLibrary {
        if (!::config.isInitialized) {
            val newConfig = TrustbadgeConfig.fromResource(context)
            if (newConfig.clientId.isNotBlank() || newConfig.clientSecret.isNotBlank()) {
                config = newConfig
            } else {
                throw Error("""
                    trustbadge-config.json file was empty or not found.
                    Please make sure the config file exists under project-root and not empty.
                """.trimIndent())
            }
        }
        return this
    }
}