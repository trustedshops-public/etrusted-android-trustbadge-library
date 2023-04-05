/*
 * Created by Ali Kabiri on 30.11.2022.
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

package com.etrusted.android.trustbadgeexample.ui.products

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.etrusted.android.trustbadge.library.ui.badge.Trustbadge
import com.etrusted.android.trustbadge.library.ui.badge.TrustbadgeContext
import com.etrusted.android.trustbadgeexample.ui.common.ImageScrProduct

@Composable
internal fun ProductScreen() {

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            ImageScrProduct()
        }

        Trustbadge(
            modifier = Modifier.align(Alignment.BottomStart),
            badgeContext = TrustbadgeContext.TrustMark,
            tsid = "X2AB6FF7BFF70A04D1D323E039D676EDB",
            channelId = "chl-7e52920a-2722-4881-9908-ecec98c716e4"
        )

    }
}

@Preview
@Composable
internal fun PreviewProductScreen() {
    ProductScreen()
}