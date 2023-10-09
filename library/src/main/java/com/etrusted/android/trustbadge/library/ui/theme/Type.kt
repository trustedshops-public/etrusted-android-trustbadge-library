/*
 * Created by Ali Kabiri on 29.11.2022.
 * Copyright (c) 2022 Trusted Shops AG
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

@file:Suppress("unused")

package com.etrusted.android.trustbadge.library.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val Typography.mobileBase: TextStyle
    @Composable
    get() = TextStyle(
        fontWeight = FontWeight(700),
        fontSize = 14.sp,
        lineHeight = 18.sp,
        color = MaterialTheme.colorScheme.TsTextBase,
    )

val Typography.mobileBodySmall: TextStyle
    @Composable
    get() = TextStyle(
        fontWeight = FontWeight(400),
        fontSize = 12.sp,
        lineHeight = 16.sp,
        color = MaterialTheme.colorScheme.TsNeutralsGrey600,
    )

val Typography.mobileBodySmallBold: TextStyle
    @Composable
    get() = TextStyle(
        fontWeight = FontWeight(700),
        fontSize = 12.sp,
        lineHeight = 16.sp,
        color = MaterialTheme.colorScheme.TsNeutralsGrey600,
    )

val Typography.mobileCaption: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(400),
        fontStyle = FontStyle.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = (0.03).sp,
        textAlign = TextAlign.Center,
    )

val Typography.mobileCaptionExtraNarrow: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(400),
        fontStyle = FontStyle.Normal,
        fontSize = 10.sp,
        lineHeight = 16.sp,
        letterSpacing = (0.03).sp,
        textAlign = TextAlign.Center,
    )

val Typography.mobileHeadline: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(700),
        fontSize = 16.sp,
        lineHeight = 16.sp,
        color = MaterialTheme.colorScheme.TsTextBase,
    )

val Typography.mobileTermsAndConditions: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(400),
        fontSize = 11.sp,
        lineHeight = 13.sp,
        color = MaterialTheme.colorScheme.TsNeutralsGrey700,
    )

val Typography.mobileBody: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(400),
        fontSize = 13.sp,
        lineHeight = 19.sp,
        color = MaterialTheme.colorScheme.TsNeutralsGrey700,
    )
