/*
 * Created by Ali Kabiri on 22.9.2023.
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

package com.etrusted.android.trustbadge.library.ui.card.protection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.etrusted.android.trustbadge.library.R
import com.etrusted.android.trustbadge.library.ui.theme.mobileBody

@Composable
fun ActiveProductListText() {

    val textList = listOf(
        stringResource(id = R.string.tcard_t_protection_confirmation_1_automatic_guarantee),
        stringResource(id = R.string.tcard_t_protection_confirmation_2_available_in_shops),
        stringResource(id = R.string.tcard_t_protection_confirmation_3_all_benefits_of_ts)
    )

    for (text in textList) {
        Row {
            Image(
                modifier = Modifier
                    .align(Alignment.Top)
                    .padding(end = 8.dp, top = 4.dp),
                painter = painterResource(id = R.drawable.ic_check_circle),
                contentDescription = stringResource(id = R.string.tcard_ic_checkmark)
            )
            Text(
                style = MaterialTheme.typography.mobileBody,
                text = text
            )
        }
    }
}