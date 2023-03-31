/*
 * Created by Ali Kabiri on 22.11.2022.
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

package com.etrusted.android.trustbadge.library.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = TsPineappleNight,
    secondary = TsNeutralsGrey100Night,
    background = TsBgNight,
    surface = TsBgNight,
)

private val LightColorScheme = lightColorScheme(
    primary = TsPineappleDay,
    secondary = TsNeutralsGrey100Day,
    background = TsBgDay,
    surface = TsBgDay,
)

/**
 * Use this setting to force the app theme to present
 * either Dark or Light modes.
 * To read the system mode, use isSystemInDarkTheme()
 * To force dark mode, use false
 * To force light mode, use true
 */
@Suppress("unused")
val ColorScheme.isLightCustom: Boolean
    @Composable
    get() = !isSystemInDarkTheme()

@Composable
fun TrustbadgeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val customIsLight = MaterialTheme.colorScheme.isLightCustom
    val colorScheme = if (darkTheme && !customIsLight) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}

val ColorScheme.TsTextBase: Color
    @Composable
    get() = if (isLightCustom) TsTextBaseDay
    else TsTextBaseNight

val ColorScheme.TsBlueAction: Color
    @Composable
    get() = if (isLightCustom) TsBlueActionDay
    else TsBlueActionNight

val ColorScheme.TsPineapple: Color
    @Composable
    get() = if (this.isLightCustom) TsPineappleDay
    else TsPineappleNight

val ColorScheme.TsBadgeBg: Color
    @Composable
    get() = if (this.isLightCustom) TsBadgeBgDay
    else TsBadgeBgNight

val ColorScheme.TsNeutralsGrey50: Color
    @Composable
    get() = if (isLightCustom) TsNeutralsGrey50Day
    else TsNeutralsGrey50Night

val ColorScheme.TsNeutralsGrey100: Color
    @Composable
    get() = if (this.isLightCustom) TsNeutralsGrey100Day
    else TsNeutralsGrey100Night

val ColorScheme.TsNeutralsGrey600: Color
    @Composable
    get() = if (isLightCustom) TsNeutralsGrey600Day
    else TsNeutralsGrey600Night

val ColorScheme.TsNeutralsGrey800: Color
    @Composable
    get() = if (isLightCustom) TsNeutralsGrey800Day
    else TsNeutralsGrey800Night