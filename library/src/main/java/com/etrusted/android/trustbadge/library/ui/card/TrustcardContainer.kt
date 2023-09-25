/*
 * Created by Ali Kabiri on 13.9.2023.
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

package com.etrusted.android.trustbadge.library.ui.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.etrusted.android.trustbadge.library.R
import com.etrusted.android.trustbadge.library.ui.theme.TsPineapple
import com.etrusted.android.trustbadge.library.ui.theme.TsTextBase
import com.etrusted.android.trustbadge.library.ui.theme.mobileHeadline


@Composable
internal fun TrustcardContainer(
    modifier: Modifier = Modifier,
    headingText: String,
    content: @Composable () -> Unit = {},
    footer: @Composable () -> Unit = {},
    onClickDismiss: () -> Unit,
) {
    Box(modifier = modifier) {

        // Rounded yellow border
        Box(modifier = Modifier
            .border(
                width = 6.dp,
                shape = RoundedCornerShape(6.dp),
                color = MaterialTheme.colorScheme.TsPineapple,
            )
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(6.dp),
            )
            .fillMaxWidth()
        ) {
            Column {
                // 1. Heading text
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 8.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterStart),
                        style = MaterialTheme.typography.mobileHeadline,
                        text = headingText,
                    )
                    IconButton(
                        modifier = Modifier
                            .align(Alignment.CenterEnd),
                        onClick = onClickDismiss) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_dismiss),
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.TsTextBase),
                            contentDescription = stringResource(id = R.string.tcard_ic_dismiss)
                        )
                    }
                }

                // 2. Trustcard content
                content()


                // 3. Footer content and footer logo
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column (
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 16.dp)
                    ) {
                        footer()
                    }
                    Image(
                        modifier = Modifier
                            .align(Alignment.BottomEnd),
                        painter = painterResource(id = R.drawable.ic_ts_footer),
                        contentDescription = stringResource(id = R.string.tcard_ic_ts_footer))
                }
            }
        }
    }
}