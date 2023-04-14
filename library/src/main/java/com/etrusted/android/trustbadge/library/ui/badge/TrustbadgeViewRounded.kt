/*
 * Created by Ali Kabiri on 6.12.2022.
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

package com.etrusted.android.trustbadge.library.ui.badge

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.etrusted.android.trustbadge.library.common.internal.TestTags
import com.etrusted.android.trustbadge.library.ui.common.ImageCircleBuyerProtection
import com.etrusted.android.trustbadge.library.ui.common.ImageCircleProductIcon
import com.etrusted.android.trustbadge.library.ui.common.ImageCircleShopIcon
import com.etrusted.android.trustbadge.library.ui.common.ImageCircleSeal

@Composable
internal fun RowScope.RoundedView(
    modifier: Modifier = Modifier,
    state: TrustbadgeState,
    badgeContext: TrustbadgeContext
) {
    Box (modifier = modifier.testTag(TestTags.TrustbadgeRounded.raw)) {
        this@RoundedView.AnimatedVisibility(
            visible = state.currentState == TrustbadgeStateValue.DEFAULT,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            ImageCircleSeal(modifier = Modifier.padding(4.dp))
        }
        this@RoundedView.AnimatedVisibility(
            visible = state.currentState == TrustbadgeStateValue.EXPANDED,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            when (badgeContext) {
                TrustbadgeContext.ShopGrade -> {
                    ImageCircleShopIcon()
                }
                is TrustbadgeContext.ProductGrade -> {
                    ImageCircleProductIcon()
                }
                TrustbadgeContext.BuyerProtection -> {
                    ImageCircleBuyerProtection()
                }
                TrustbadgeContext.TrustMark -> {
                    // Intentionally left empty since Kotlin requires 'when' expression
                    // to be exhaustive, it is necessary to add all branches (or 'else').
                    // In this state, the Trustmark icon remains the same, therefore
                    // this block remains empty.
                }
            }
        }
    }
}