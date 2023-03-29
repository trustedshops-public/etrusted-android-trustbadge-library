/*
 * MIT License
 *
 * Created by Ali Kabiri on 22.11.2022.
 * Copyright (c) 2022 Trusted Shops AG
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

import android.graphics.PointF
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.etrusted.android.trustbadge.library.ui.theme.TsNeutralsGrey100
import com.etrusted.android.trustbadge.library.ui.theme.TsPineapple
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private val StarShape = GenericShape { size, _ ->

    val corners = 5 //default
    val smoothness = 4.0
    val center = PointF(size.width / 2.0f, size.height / 2.0f)
    val radius: Float
    val innerRadius: Float
    if (size.width > size.height) {
        radius = size.height / corners
        innerRadius = size.height / 2
    } else {
        radius = size.width / corners
        innerRadius = size.width / 2
    }

    // starting angle from top to bottom
    var currentAngle = PI.toFloat() / 2
    // calculate how much we need to move with each star corner
    val angleAdjustment = PI.toFloat() * 2.0f / corners * 2.0f

    reset()

    moveTo(
        (center.x + radius * cos(currentAngle)),
        (center.y + radius * sin(currentAngle))
    )

    for (i in 0 until corners) {
        val sinAngle = sin(currentAngle)
        val cosAngle = cos(currentAngle)

        lineTo(
            center.x + radius * cosAngle,
            center.y + radius * sinAngle
        )
        lineTo(
            center.x + innerRadius * cos(currentAngle + angleAdjustment / smoothness).toFloat(),
            center.y + innerRadius * sin(currentAngle + angleAdjustment / smoothness).toFloat()
        )

        currentAngle += angleAdjustment
    }
}

@Composable
fun Star(color: Color = MaterialTheme.colorScheme.TsPineapple,
         colorBg: Color = MaterialTheme.colorScheme.TsNeutralsGrey100,
         size: Dp = 18.dp,
         fill: Float) {
    val sizePx = with(LocalDensity.current) { size.toPx() }

    val gradient =
        Brush.horizontalGradient(
            100f to color,
            100f to colorBg,
            startX = 0f,
            endX = sizePx * fill
        )

    Box(
        modifier = Modifier
            .padding(0.dp)
            .size(size, size)
            .background(
                brush = gradient,
                shape = StarShape
            )
    )
}