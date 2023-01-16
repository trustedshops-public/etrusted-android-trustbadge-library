/*
 * Created by Ali Kabiri on 6.12.2022.
 * Copyright (c) 2022 Trusted Shops GmbH
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

package com.etrusted.android.trustbadge.library.ui.badge

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.etrusted.android.trustbadge.library.R
import com.etrusted.android.trustbadge.library.repository.TrustbadgeRepository
import com.etrusted.android.trustbadge.library.ui.badge.TrustbadgeContext.SHOP_GRADE
import com.etrusted.android.trustbadge.library.ui.theme.TrustbadgeTheme
import com.etrusted.android.trustbadge.library.ui.theme.TsBadgeBg
import com.etrusted.android.trustbadge.library.ui.theme.TsNeutralsGrey50
import kotlinx.coroutines.delay

private const val TAG = "TrustbadgeView"

@Composable
fun Trustbadge(
    modifier: Modifier = Modifier,
    state: TrustbadgeState = rememberTrustbadgeState(),
    badgeContext: TrustbadgeContext = SHOP_GRADE,
    tsid: String,
) {

    AnimatedVisibility(
        modifier = modifier,
        visible = state.currentState != TrustbadgeStateValue.INVISIBLE
    ) {

        ElevatedButton(
            modifier = Modifier
                .animateContentSize()
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .height(66.dp)
                .defaultMinSize(minWidth = 66.dp),
            contentPadding = PaddingValues(start = 66.dp),
            onClick = {},
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.TsNeutralsGrey50),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.TsBadgeBg
            )
        ) {
            ExpandedView(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                state = state, badgeContext = badgeContext)
        }

        ElevatedButton(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 24.dp),
            contentPadding = PaddingValues(0.dp),
            onClick = {},
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.TsBadgeBg
            )
        ) {

            RoundedView(state, badgeContext)

            // delete this
            Text(text = stringResource(id = R.string.client_id))
        }
    }

    LaunchedEffect(null) {
        val repo = TrustbadgeRepository()
        val resp = repo.getTrustbadgeData(tsid = tsid)
        if (resp.isSuccess) {
            Log.d(TAG, "name: ${resp.getOrNull()?.shop?.tsid}")
        } else {
            Log.d(TAG, "error: ${resp.exceptionOrNull()?.message}")
        }
    }

    LaunchedEffect(null) {
        delay(1000)
        state.expand()
        delay(3000)
        state.retract()
    }
}

@Preview
@Composable
fun PreviewTrustbadge() {
    TrustbadgeTheme {
        Trustbadge(tsid = "X330A2E7D449E31E467D2F53A55DDD070")
    }
}