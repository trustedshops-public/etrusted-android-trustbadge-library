/*
 * Created by Ali Kabiri on 25.9.2023.
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

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.etrusted.android.trustbadge.library.common.internal.TestContextWrapper
import com.etrusted.android.trustbadge.library.common.internal.TestTags
import com.etrusted.android.trustbadge.library.ui.card.protection.TermsAndConditionsAndPrivacyPolicyText
import com.etrusted.android.trustbadge.library.ui.theme.TrustbadgeTheme
import org.junit.Rule
import org.junit.Test

internal class TermsAndConditionsAndPrivacyPolicyTextAndroidTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testClickOnTermsAndConditionsWork() {

        // arrange
        val baseContext = InstrumentationRegistry.getInstrumentation().targetContext
        val testContext = TestContextWrapper(baseContext)
        composeTestRule.setContent {
            TrustbadgeTheme(darkTheme = false) {
                TermsAndConditionsAndPrivacyPolicyText(testContext)
            }
        }

        // act
        composeTestRule.waitForIdle()
        val sut = composeTestRule.onNodeWithTag(TestTags.TextTermsAndConditionsAndPrivacyPolicy.raw)

        // assert
        sut.assertExists()
    }

    @Test
    fun testClickOnButtonWorksWithoutDefaultModifier() {

        // arrange
        val baseContext = InstrumentationRegistry.getInstrumentation().targetContext
        val testContext = TestContextWrapper(baseContext)
        composeTestRule.setContent {
            TrustbadgeTheme(darkTheme = true) {
                TermsAndConditionsAndPrivacyPolicyText(testContext)
            }
        }

        // act
        composeTestRule.waitForIdle()
        val sut = composeTestRule.onNodeWithText(text = "Privacy", substring = true)

        // assert
        sut.assertExists()
    }
}