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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.etrusted.android.trustbadge.library.R
import com.etrusted.android.trustbadge.library.common.internal.TestTags
import com.etrusted.android.trustbadge.library.ui.common.openLinkInExternalBrowser
import com.etrusted.android.trustbadge.library.ui.theme.TsBlueAction
import com.etrusted.android.trustbadge.library.ui.theme.mobileBodySmall

@OptIn(ExperimentalTextApi::class)
@Composable
fun TermsAndConditionsAndPrivacyPolicyText(
    context: Context
) {
    val termsAndConditionsAndPrivacyLink = stringResource(
        id = R.string.tcard_link_terms_and_conditions_and_privacy_policy)
    val onClickTermsAndConditionsAndPrivacyPolicyLink: () -> Unit = {
        openLinkInExternalBrowser(
            context = context,
            link = termsAndConditionsAndPrivacyLink
        )
    }

    val hintString = buildAnnotatedString {
        withStyle(style = MaterialTheme.typography.mobileBodySmall.toSpanStyle()) {
            append(stringResource(id = R.string.tcard_t_hint_1_we_may_ask))
        }
        withStyle(style = MaterialTheme.typography.mobileBodySmall.copy(
            color = MaterialTheme.colorScheme.TsBlueAction
        ).toSpanStyle()) {
            val text = stringResource(id = R.string.tcard_t_hint_2_terms_and_conditions)
            withAnnotation(tag = "tandc", annotation = "Bold") {
                append(text)
            }
        }
        withStyle(style = MaterialTheme.typography.mobileBodySmall.toSpanStyle()) {
            append(stringResource(id = R.string.tcard_t_hint_3_and))
        }
        withStyle(
            style = MaterialTheme.typography.mobileBodySmall.copy(
                color = MaterialTheme.colorScheme.TsBlueAction
            ).toSpanStyle()) {
            val text = stringResource(id = R.string.tcard_t_hint_4_privacy_policy)
            withAnnotation(tag = "privacy", annotation = "Bold") {
                append(text)
            }
        }
    }

    ClickableText(
        text = hintString,
        modifier = Modifier
            .padding(16.dp)
            .testTag(TestTags.TextTermsAndConditionsAndPrivacyPolicy.raw)
    ) { offset ->
        hintString.getStringAnnotations(tag = "tandc", start = offset, end = offset)
            .firstOrNull()
            ?.let {
                onClickTermsAndConditionsAndPrivacyPolicyLink()
            }
        hintString.getStringAnnotations(tag = "privacy", start = offset, end = offset)
            .firstOrNull()
            ?.let {
                onClickTermsAndConditionsAndPrivacyPolicyLink()
            }
    }
}