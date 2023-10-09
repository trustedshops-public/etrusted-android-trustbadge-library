/*
 * Created by Ali Kabiri on 11.5.2023.
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

enum class CurrencyCode(val rawValue: String, val symbol: String) {

    CHF(rawValue = "CHF", symbol = "CHF"),
    EUR(rawValue = "EUR", symbol = "€"),
    GBP(rawValue = "GBP", symbol = "£"),
    PLN(rawValue = "PLN", symbol = "zł"),
    NOK(rawValue = "NOK", symbol = "kr"),
    SEK(rawValue = "SEK", symbol = "kr"),
    DKK(rawValue = "DKK", symbol = "kr"),
    RON(rawValue = "RON", symbol = "L"),
    CZK(rawValue = "CZK", symbol = "Kč");

    companion object {
        fun forRaw(raw: String): CurrencyCode? = values().firstOrNull { it.rawValue == raw }
    }
}