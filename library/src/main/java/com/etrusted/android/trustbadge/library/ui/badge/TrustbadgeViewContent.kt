/*
 * Created by Ali Kabiri on 31.3.2023.
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

package com.etrusted.android.trustbadge.library.ui.badge

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel
import com.etrusted.android.trustbadge.library.common.internal.TestTags


/**
 * Trustbadge content allowing usage of custom viewModel in tests
 */
@Composable
internal fun TrustbadgeContent(
    modifier: Modifier,
    viewModel: ITrustbadgeViewModel = viewModel<TrustbadgeViewModel>(),
    state: TrustbadgeState,
    badgeContext: TrustbadgeContext,
    tsid: String,
    channelId: String
) {
    val context = LocalContext.current

    val trustbadgeData by viewModel.trustbadgeData.collectAsState()
    val guarantee by viewModel.guarantee.collectAsState()
    val productGrade by viewModel.productGrade.collectAsState()
    val productData by viewModel.productData.collectAsState()

    AnimatedVisibility(
        modifier = modifier.testTag(TestTags.Trustbadge.raw),
        visible = state.currentState != TrustbadgeStateValue.INVISIBLE
    ) {

        TrustbadgeViewExpandedElevated(
            state = state,
            badgeContext = badgeContext,
            trustbadgeData = trustbadgeData,
            guarantee = guarantee,
            productGrade = productGrade,
        )
        TrustbadgeViewRoundedElevated(
            state = state,
            badgeContext = badgeContext,
            productData = productData,
        )
    }

    LaunchedEffect(null) {
        viewModel.fetchTrustbadgeData(tsid, channelId)
        viewModel.fetchGuarantee(tsid, channelId)
        if (badgeContext is TrustbadgeContext.ProductGrade) {
            viewModel.fetchProductGrade(channelId, badgeContext.sku)
            viewModel.fetchProductDetail(channelId, badgeContext.sku)
        }

        // show the expanded state automatically for the context.
        // if the context is not set to TRUSTMARK, expand the badge
        state.present(context = context, badgeContext = badgeContext)
    }
}