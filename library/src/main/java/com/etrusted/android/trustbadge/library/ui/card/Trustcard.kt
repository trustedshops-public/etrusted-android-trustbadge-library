/*
 * Created by Ali Kabiri on 2.10.2023.
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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.etrusted.android.trustbadge.library.common.internal.ExcludeFromJacocoGeneratedReport
import com.etrusted.android.trustbadge.library.common.internal.TestTags
import com.etrusted.android.trustbadge.library.model.CurrencyCode
import com.etrusted.android.trustbadge.library.model.OrderDetails
import com.etrusted.android.trustbadge.library.ui.badge.TrustbadgeContext
import com.etrusted.android.trustbadge.library.ui.badge.TrustbadgeState
import com.etrusted.android.trustbadge.library.ui.badge.TrustbadgeStateValue
import com.etrusted.android.trustbadge.library.ui.badge.rememberTrustbadgeState
import com.etrusted.android.trustbadge.library.ui.card.protection.TrustcardProtection
import com.etrusted.android.trustbadge.library.ui.card.protection.TrustcardProtectionConfirmation
import com.etrusted.android.trustbadge.library.ui.theme.TrustbadgeTheme

@Composable
fun Trustcard(
    badgeState: TrustbadgeState,
    cardState: TrustcardState = rememberTrustcardState(),
    badgeContext: TrustbadgeContext,
    onClickDismiss: () -> Unit,
) {

    if (
        badgeContext is TrustbadgeContext.BuyerProtection &&
        badgeContext.orderDetails != null) {

        AnimatedVisibility(
            modifier = Modifier.testTag(TestTags.Trustbadge.raw),
            visible = badgeState.currentState == TrustbadgeStateValue.EXPANDED_AS_CARD) {

            when (cardState.currentState) {
                TrustcardStateValue.CLASSIC_PROTECTION -> {
                    TrustcardProtection(
                        orderDetails = badgeContext.orderDetails,
                        onClickDismiss = onClickDismiss,
                        onClickProtectPurchase = {
                            cardState.showConfirmation()
                        },
                    )
                }
                TrustcardStateValue.PROTECTION_CONFIRMATION -> {

                    TrustcardProtectionConfirmation(
                        orderAmount = badgeContext.orderDetails.amount,
                        onClickDismiss = onClickDismiss
                    )
                }
            }
        }
    }
}

@Composable
@Preview
@ExcludeFromJacocoGeneratedReport
fun TrustcardPreview() {
    val badgeState = rememberTrustbadgeState()
    badgeState.showAsCard()
    val cardContext = TrustbadgeContext.BuyerProtection(
        orderDetails = OrderDetails(
            number = "123456789",
            amount = "100.0",
            currency = CurrencyCode.EUR,
            paymentType = "PayPal",
            estimatedDeliveryDate = "2022-11-30",
            buyerEmail = "john@gmx.de"
        )
    )
    TrustbadgeTheme {
        Trustcard(
            badgeState = badgeState,
            badgeContext = cardContext,
            onClickDismiss = {
                badgeState.hideCard()
            }
        )
    }
}