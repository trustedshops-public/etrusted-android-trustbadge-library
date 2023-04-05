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

package com.etrusted.android.trustbadge.library.ui.rating

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.etrusted.android.trustbadge.library.ui.theme.TsNeutralsGrey100
import com.etrusted.android.trustbadge.library.ui.theme.TsPineapple

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Float,
    color: Color = MaterialTheme.colorScheme.TsPineapple,
    colorBg: Color = MaterialTheme.colorScheme.TsNeutralsGrey100,
    starSize: Dp = 18.dp,
    gapSize: Dp = 2.dp,
    numStars: Int = 5,
) {
    Row(modifier = modifier) {
        for (index in 0 until numStars) {
            Star(
                color = color,
                colorBg = colorBg,
                size = starSize,
                fill = if (rating - index < 0) 0f else rating - index
            )
            if (index != numStars) {
                Spacer(modifier = Modifier.width(gapSize))
            }
        }
    }
}