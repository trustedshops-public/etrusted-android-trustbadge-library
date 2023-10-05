/*
 * Created by Ali Kabiri on 5.10.2023.
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
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithTag
import com.etrusted.android.trustbadge.library.common.internal.GoldenNames
import com.etrusted.android.trustbadge.library.common.internal.TestTags
import com.etrusted.android.trustbadge.library.common.internal.assertScreenshotMatchesGolden
import com.etrusted.android.trustbadge.library.common.internal.getFakeGuarantee
import com.etrusted.android.trustbadge.library.common.internal.getFakeOrderDetails
import com.etrusted.android.trustbadge.library.common.internal.saveScreenshot
import com.etrusted.android.trustbadge.library.ui.badge.TrustbadgeAndroidTest
import com.etrusted.android.trustbadge.library.ui.badge.TrustbadgeContext
import com.etrusted.android.trustbadge.library.ui.badge.rememberTrustbadgeState
import com.etrusted.android.trustbadge.library.ui.theme.TrustbadgeTheme
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

internal class TrustcardNightAndroidTest: TrustbadgeAndroidTest() {

    override val goldenName = GoldenNames.GoldenTrustcardNight.raw +
            if (isCI) "-ci" else ""

    private val fakeOrderDetails = getFakeOrderDetails()
    private val fakeGuarantee = getFakeGuarantee()

    override fun showContent() {
        composeTestRule.setContent {

            val badgeState = rememberTrustbadgeState().apply {
                showAsCard()
            }
            val cardState = rememberTrustcardState()

            TrustbadgeTheme(darkTheme = true) {
                Column {
                    Trustcard(
                        badgeState = badgeState,
                        cardState = cardState,
                        badgeContext = TrustbadgeContext.BuyerProtection(
                            orderDetails = fakeOrderDetails
                        ),
                        guarantee = fakeGuarantee,
                        onClickDismiss = {},
                    )
                }
            }
        }
    }

//    @Ignore("activate to generate fresh screenshots")
    @Test
    override fun generateScreenshot() {

        // arrange
        showContent() // wait to finish expand animation

        // act
        composeTestRule.mainClock.advanceTimeBy(5000)
        composeTestRule.waitForIdle()
        val sut = composeTestRule.onNodeWithTag(TestTags.Trustcard.raw)
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
        val sut = composeTestRule.onNodeWithTag(TestTags.Trustcard.raw)

        // assert
        sut.assertExists()
        assertScreenshotMatchesGolden(goldenName, sut)
    }
}