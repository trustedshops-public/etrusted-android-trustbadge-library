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

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.etrusted.android.trustbadge.library.R
import com.etrusted.android.trustbadge.library.common.internal.TestTags
import com.etrusted.android.trustbadge.library.model.OrderDetails
import com.etrusted.android.trustbadge.library.ui.card.TrustcardContainer
import com.etrusted.android.trustbadge.library.ui.common.ActionButton
import com.etrusted.android.trustbadge.library.ui.common.openLinkInExternalBrowser
import com.etrusted.android.trustbadge.library.ui.theme.TsTextBase
import com.etrusted.android.trustbadge.library.ui.theme.mobileBody
import com.etrusted.android.trustbadge.library.ui.theme.mobileCaption
import com.etrusted.android.trustbadge.library.ui.theme.mobileTermsAndConditions

@Composable
fun TrustcardProtection(
    modifier: Modifier = Modifier,
    orderDetails: OrderDetails,
    onClickProtectPurchase: () -> Unit = {},
    onClickDismiss: () -> Unit = {},
    context: Context = LocalContext.current
) {

    val imprintLink = stringResource(id = R.string.tcard_link_imprint)

    val onClickImprint = {
        openLinkInExternalBrowser(
            context = context,
            link = imprintLink,
        )
    }

    TrustcardContainer(
        modifier = modifier
            .testTag(TestTags.TrustcardProtection.raw),
        headingText = stringResource(id = R.string.tcard_t_protect_your_purchase),
        content = {

            // 1. shop details and order amount
            Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                Column(Modifier.weight(1f)) {
                    Text(
                        style = MaterialTheme.typography.mobileCaption,
                        color = MaterialTheme.colorScheme.TsTextBase,
                        text = stringResource(id = R.string.tcard_t_shop)
                    )
                    Text(
                        style = MaterialTheme.typography.mobileBody,
                        color = MaterialTheme.colorScheme.TsTextBase,
                        text = "shop-name.com")
                }
                Box(modifier = Modifier.weight(1f)) {
                    Column(modifier = Modifier.align(Alignment.CenterEnd)) {
                        val orderAmountText = buildAnnotatedString {
                            append(orderDetails.currency.symbol)
                            append(" ")
                            append(orderDetails.amount)
                        }
                        Text(
                            style = MaterialTheme.typography.mobileCaption,
                            color = MaterialTheme.colorScheme.TsTextBase,
                            text = stringResource(id = R.string.tcard_t_order_amount)
                        )
                        Text(
                            style = MaterialTheme.typography.mobileBody,
                            color = MaterialTheme.colorScheme.TsTextBase,
                            text = orderAmountText)
                    }
                }
            }

            // 2. active product list and terms and conditions
            Column(modifier = Modifier
                .padding(16.dp)
            ) {
                ActiveProductListText()

                Spacer(modifier = Modifier.height(16.dp))

                TermsAndConditionsAndPrivacyPolicyText(
                    context = context,
                )
            }

            // 3. protect your purchase button
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = onClickProtectPurchase,
            ) {
                Text(text = stringResource(id = R.string.tcard_bt_protect_your_purchase))
            }
        },
        footer = {
            Text(
                modifier = Modifier
                    .clickable(enabled = true, onClick = onClickImprint),
                style = MaterialTheme.typography.mobileTermsAndConditions,
                text = stringResource(id = R.string.tcard_t_imprint),
            )
        },
        onClickDismiss = onClickDismiss,
    )
}