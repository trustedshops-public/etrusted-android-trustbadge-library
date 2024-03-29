/*
 * Created by Ali Kabiri on 11.9.2023.
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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import androidx.test.platform.app.InstrumentationRegistry
import com.etrusted.android.trustbadge.library.R
import com.etrusted.android.trustbadge.library.common.internal.GoldenNames
import com.etrusted.android.trustbadge.library.common.internal.TestContextWrapper
import com.etrusted.android.trustbadge.library.common.internal.TestTags
import com.etrusted.android.trustbadge.library.common.internal.assertScreenshotMatchesGolden
import com.etrusted.android.trustbadge.library.common.internal.getFakeOrderDetails
import com.etrusted.android.trustbadge.library.common.internal.saveScreenshot
import com.etrusted.android.trustbadge.library.model.CurrencyCode
import com.etrusted.android.trustbadge.library.ui.badge.TrustbadgeAndroidTest
import com.etrusted.android.trustbadge.library.ui.card.protection.TrustcardProtectionConfirmation
import com.etrusted.android.trustbadge.library.ui.theme.TrustbadgeTheme
import com.google.common.truth.Truth.assertThat
import org.junit.Ignore
import org.junit.Test

internal class TrustcardProtectionConfirmationAndroidTest: TrustbadgeAndroidTest() {

    override val goldenName = GoldenNames.GoldenTrustcardClassicProtectionConfirmation.raw +
            if (isCI) "-ci" else ""

    override fun showContent() {
        composeTestRule.setContent {
            TrustbadgeTheme {
                TrustcardProtectionConfirmation(
                    orderDetails = getFakeOrderDetails(),
                    guaranteeAmount = "1000",
                    onClickDismiss = {},
                )
            }
        }
    }

    @Ignore("activate to generate fresh screenshots")
    @Test
    override fun generateScreenshot() {

        // arrange
        showContent() // wait to finish expand animation

        // act
        composeTestRule.mainClock.advanceTimeBy(5000)
        composeTestRule.waitForIdle()
        val sut = composeTestRule.onNodeWithTag(TestTags.TrustcardProtectionConfirmation.raw)
        val bmp = sut.captureToImage().asAndroidBitmap()
        saveScreenshot(goldenName, bmp)

        // assert
        sut.assertExists()

    }

    @Test
    override fun testScreenshotMatchesGolden() {

        // arrange
        showContent()

        // act
        composeTestRule.mainClock.advanceTimeBy(5000) // wait to finish expand animation
        composeTestRule.waitForIdle()
        val sut = composeTestRule.onNodeWithTag(TestTags.TrustcardProtectionConfirmation.raw)

        // assert
        sut.assertExists()
        assertScreenshotMatchesGolden(goldenName, sut)
    }

    @Test
    internal fun testClickOnImprintAndDataProtectionCallsStartActivityOnContext() {

        // arrange
        val baseContext = InstrumentationRegistry.getInstrumentation().targetContext
        val testContext = TestContextWrapper(baseContext)
        composeTestRule.setContent {
            TrustbadgeTheme(darkTheme = true) {
                Column {
                    TrustcardProtectionConfirmation(
                        orderDetails = getFakeOrderDetails(),
                        guaranteeAmount = "1000",
                        context = testContext,
                        onClickDismiss = {},
                    )
                }
            }
        }

        // act
        composeTestRule.waitForIdle()
        val sut = composeTestRule
            .onNodeWithText("Imprint", ignoreCase = true, substring = true)
        sut.performClick()
        composeTestRule.waitForIdle()

        // assert
        assertThat(testContext.isStartActivityCalled).isTrue()
    }

    @Test
    internal fun testClickOnTermsAndConditionsCallsStartActivityOnContext() {

        // arrange
        val baseContext = InstrumentationRegistry.getInstrumentation().targetContext
        val testContext = TestContextWrapper(baseContext)
        composeTestRule.setContent {
            TrustbadgeTheme(darkTheme = false) {
                Column {
                    TrustcardProtectionConfirmation(
                        modifier = Modifier,
                        orderDetails = getFakeOrderDetails(),
                        guaranteeAmount = "1000",
                        context = testContext,
                        onClickDismiss = {},
                    )
                }
            }
        }

        // act
        composeTestRule.waitForIdle()
        val sut = composeTestRule
            .onNodeWithText("Terms", ignoreCase = true, substring = true)
        sut.performClick()
        composeTestRule.waitForIdle()

        // assert
        assertThat(testContext.isStartActivityCalled).isTrue()
    }

    @Test
    fun testCustomModifierIsApplied() {
        // arrange
        val customModifier = Modifier
            .padding(10.dp)

        composeTestRule.setContent {
            TrustbadgeTheme {
                TrustcardProtectionConfirmation(
                    modifier = customModifier,
                    orderDetails = getFakeOrderDetails(),
                    guaranteeAmount = "1000 €",
                    onClickDismiss = {},
                )
            }
        }

        // act
        composeTestRule.waitForIdle()
        val sut = composeTestRule.onNodeWithTag(TestTags.TrustcardProtectionConfirmation.raw)

        // assert
        sut.assertExists()
    }

    @Test
    fun testOnClickDismissIsCalled() {

        // arrange
        var isClicked = false
        val fakeOnClickToDismiss = {
            isClicked = true
        }
        composeTestRule.setContent {
            TrustbadgeTheme {
                TrustcardProtectionConfirmation(
                    orderDetails = getFakeOrderDetails(),
                    guaranteeAmount = "1000",
                    onClickDismiss = fakeOnClickToDismiss,
                )
            }
        }

        // act
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag(TestTags.TrustcardContainerButtonDismiss.raw).performClick()
        composeTestRule.waitForIdle()

        // assert
        assertThat(isClicked).isTrue()
    }

    @Test
    fun testDefaultDismissButton() {

        // arrange
        composeTestRule.setContent {
            TrustbadgeTheme {
                TrustcardProtectionConfirmation(
                    orderDetails = getFakeOrderDetails(),
                    guaranteeAmount = "1000",
                    onClickDismiss = {},
                )
            }
        }

        // act
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag(TestTags.TrustcardContainerButtonDismiss.raw).performClick()

        // assert
        // default dismiss button should do nothing
        composeTestRule.onNodeWithTag(TestTags.TrustcardProtectionConfirmation.raw).assertExists()
    }

    @Test
    fun testHeadingTextWithDifferentOrderAmount() {
        // arrange
        val guaranteeAmount = "100"
        val guaranteeCurrency = CurrencyCode.GBP
        var expectedText = ""
        composeTestRule.setContent {
            expectedText = stringResource(id = R.string.tcard_t_heading_protection_confirmation) +
                    " " + guaranteeCurrency.symbol +
                    " " + guaranteeAmount +
                    stringResource(id = R.string.tcard_t_heading_exclamation_mark)
            TrustbadgeTheme {
                TrustcardProtectionConfirmation(
                    orderDetails = getFakeOrderDetails().copy(
                        currency = guaranteeCurrency,
                    ),
                    guaranteeAmount = guaranteeAmount,
                    onClickDismiss = {},
                )
            }
        }

        // act
        val sut = composeTestRule.onNodeWithText(expectedText)

        // assert
        assertThat(expectedText).isNotEmpty()
        sut.assertExists()
    }

    @Test
    fun testUsingDefaultArguments() {

        // arrange
        composeTestRule.setContent {
            TrustbadgeTheme {
                TrustcardProtectionConfirmation(
                    orderDetails = getFakeOrderDetails(),
                    guaranteeAmount = "1000 €",
                    onClickDismiss = {},
                )
            }
        }

        // act
        composeTestRule.waitForIdle()
        val sut = composeTestRule.onNodeWithTag(TestTags.TrustcardProtectionConfirmation.raw)

        // assert
        sut.assertExists()
    }
}