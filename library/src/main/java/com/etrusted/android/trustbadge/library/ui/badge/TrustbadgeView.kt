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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.etrusted.android.trustbadge.library.common.internal.TestTags
import com.etrusted.android.trustbadge.library.ui.badge.TrustbadgeContext.ShopGrade
import com.etrusted.android.trustbadge.library.ui.badge.TrustbadgeContext.TrustMark
import com.etrusted.android.trustbadge.library.ui.theme.TrustbadgeTheme
import com.etrusted.android.trustbadge.library.ui.theme.TsBadgeBg
import com.etrusted.android.trustbadge.library.ui.theme.TsNeutralsGrey50
import kotlinx.coroutines.delay

@Composable
fun Trustbadge(
    modifier: Modifier = Modifier,
    state: TrustbadgeState = rememberTrustbadgeState(),
    badgeContext: TrustbadgeContext = ShopGrade,
    tsid: String,
    channelId: String,
) {
    val viewModel: TrustbadgeViewModel = viewModel()
    val trustbadgeData by viewModel.trustbadgeData.collectAsState()

    AnimatedVisibility(
        modifier = modifier.testTag(TestTags.Trustbadge.raw),
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
                modifier = Modifier.align(Alignment.CenterVertically),
                state = state, badgeContext = badgeContext,
                rating = trustbadgeData?.shop?.rating)
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

            RoundedView(modifier = Modifier.testTag(TestTags.TrustbadgeDefault.raw),
                state = state, badgeContext = badgeContext)
        }
    }

    LaunchedEffect(null) {
        viewModel.fetchTrustbadgeData(tsid, channelId)
    }

    LaunchedEffect(null) {
        // automatically show the expanded state only if the context is not set to TRUSTMARK
        // The TRUSTMARK state only shows the badge in circle form
        if (badgeContext != TrustMark) {
            delay(1000)
            state.expand()
            delay(3000)
            state.retract()
        }
    }
}

@Preview
@Composable
fun PreviewTrustbadge() {
    TrustbadgeTheme {
        Trustbadge(
            tsid = "X330A2E7D449E31E467D2F53A55DDD070",
            channelId = "chl-bcd573bb-de56-45d6-966a-b46d63be4a1b",
        )
    }
}