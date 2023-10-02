/*
 * Created by Ali Kabiri on 26.9.2023.
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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.etrusted.android.trustbadge.library.R
import com.etrusted.android.trustbadge.library.common.internal.TestTags
import com.etrusted.android.trustbadge.library.ui.card.TrustcardContainer
import com.etrusted.android.trustbadge.library.ui.common.openLinkInExternalBrowser
import com.etrusted.android.trustbadge.library.ui.theme.TsTextBase
import com.etrusted.android.trustbadge.library.ui.theme.mobileCaption
import com.etrusted.android.trustbadge.library.ui.theme.mobileTermsAndConditions

@Composable
fun TrustcardProtectionConfirmation(
    modifier: Modifier = Modifier,
    orderAmount: String,
    onClickDismiss: () -> Unit,
    context: Context = LocalContext.current,
) {

    val termsAndConditionsLink =
        stringResource(id = R.string.tcard_link_terms_and_conditions_and_privacy_policy)
    val imprintAndDataProtectionLink =
        stringResource(id = R.string.tcard_link_imprint_and_data_protection)

    val onClickTermsAndConditions: () -> Unit = {
        openLinkInExternalBrowser(
            context = context,
            link = termsAndConditionsLink,
        )
    }

    val onClickImprintAndDataProtection: () -> Unit = {
        openLinkInExternalBrowser(
            context = context,
            link = imprintAndDataProtectionLink,
        )
    }

    val headingText = stringResource(id = R.string.tcard_t_heading_protection_confirmation) +
            " " + orderAmount + " " +
            stringResource(id = R.string.tcard_t_heading_exclamation_mark)


    TrustcardContainer(
        modifier = modifier
            .testTag(TestTags.TrustcardProtectionConfirmation.raw),
        headingText = headingText,
        content = {
            Box(modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(
                    style = MaterialTheme.typography.mobileCaption,
                    color = MaterialTheme.colorScheme.TsTextBase,
                    text = stringResource(id = R.string.tcard_t_ts_will_send_you_an_email)
                )
            }
        },
        footer = {
            Text(
                modifier = Modifier
                    .clickable(enabled = true, onClick = onClickTermsAndConditions),
                style = MaterialTheme.typography.mobileTermsAndConditions,
                text = stringResource(id = R.string.tcard_t_hint_2_terms_and_conditions),
            )
            Text(
                modifier = Modifier
                    .clickable(enabled = true, onClick = onClickImprintAndDataProtection),
                style = MaterialTheme.typography.mobileTermsAndConditions,
                text = stringResource(id = R.string.tcard_t_imprint_and_data_protection),
            )
        },
        onClickDismiss = onClickDismiss
    )
}