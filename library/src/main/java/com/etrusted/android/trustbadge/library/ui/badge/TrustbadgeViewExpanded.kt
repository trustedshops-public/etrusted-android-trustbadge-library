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
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.etrusted.android.trustbadge.library.R
import com.etrusted.android.trustbadge.library.ui.rating.RatingBar
import com.etrusted.android.trustbadge.library.ui.theme.*

@Composable
internal fun RowScope.ExpandedView(
    modifier: Modifier,
    state: TrustbadgeState, badgeContext: TrustbadgeContext
) {

    val excellentShopRevs = buildAnnotatedString {
        withStyle(style = MaterialTheme.typography.mobileBase.toSpanStyle()) {
            append(stringResource(id = R.string.tbadge_t_score_excellent))
        }
        append(' ')
        withStyle(style = MaterialTheme.typography.mobileBase.toSpanStyle().copy(
            fontWeight = FontWeight.Normal
        )) {
            append(stringResource(id = R.string.tbadge_t_score_trail_shop_reviews))
        }
    }
    val excellentProductRevs = buildAnnotatedString {
        withStyle(style = MaterialTheme.typography.mobileBase.toSpanStyle()) {
            append(stringResource(id = R.string.tbadge_t_score_excellent))
        }
        append(' ')
        withStyle(style = MaterialTheme.typography.mobileBase.toSpanStyle().copy(
            fontWeight = FontWeight.Normal
        )) {
            append(stringResource(id = R.string.tbadge_t_score_trail_product_reviews))
        }
    }
    val startPadding = if (badgeContext == TrustbadgeContext.BUYER_PROTECTION) 16.dp else 0.dp
    AnimatedVisibility (
        modifier = modifier.align(Alignment.CenterVertically),
        visible = state.currentState == TrustbadgeStateValue.EXPANDED
    ) {

        // rating title and stars ⭐️
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = startPadding, end = 24.dp)) {

            when (badgeContext) {

                TrustbadgeContext.SHOP_GRADE, TrustbadgeContext.PRODUCT_GRADE -> {
                    val starTitle =
                        if (badgeContext == TrustbadgeContext.SHOP_GRADE) excellentShopRevs
                        else excellentProductRevs
                    Text(modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = starTitle)
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        RatingBar(rating = 4.4f)
                        Text(text = buildAnnotatedString {
                            withStyle(style = MaterialTheme.typography.mobileBase.toSpanStyle()
                                .copy(color = MaterialTheme.colorScheme.TsNeutralsGrey800)) {
                                append("4.80")
                            }
                            withStyle(style = MaterialTheme.typography.mobileBase.toSpanStyle()
                                .copy(color = MaterialTheme.colorScheme.TsNeutralsGrey600)) {
                                append("/5.00")
                            }
                        })
                    }
                }
                TrustbadgeContext.BUYER_PROTECTION -> {
                    Text(text = stringResource(
                        id = R.string.tbadge_t_buyer_protection_independent),
                        style = MaterialTheme.typography.mobileBase)
                    Text(text = buildAnnotatedString {
                        withStyle(style = MaterialTheme.typography.mobileBodySmall.toSpanStyle()) {
                            append(stringResource(id = R.string.tbadge_t_buyer_protection_your_purchase))
                        }
                        append(' ')
                        withStyle(style = MaterialTheme.typography.mobileBodySmallBold.toSpanStyle()) {
                            append(stringResource(id = R.string.tbadge_t_buyer_protection_your_purchase_amount_2500))
                        }
                    })
                }
            }
        }
    }
}