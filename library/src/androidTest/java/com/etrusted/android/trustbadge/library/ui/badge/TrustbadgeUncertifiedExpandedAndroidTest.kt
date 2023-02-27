/*
 * Created by Ali Kabiri on 25.2.2023.
 * Copyright (c) 2023 Trusted Shops GmbH
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

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.platform.app.InstrumentationRegistry
import com.etrusted.android.trustbadge.library.common.internal.GoldenNames.GoldenTrustbadgeUncertifiedExpanded
import com.etrusted.android.trustbadge.library.common.internal.assertScreenshotMatchesGolden
import com.etrusted.android.trustbadge.library.common.internal.TestTags
import com.etrusted.android.trustbadge.library.common.internal.saveScreenshot
import com.etrusted.android.trustbadge.library.ui.theme.TrustbadgeTheme
import org.junit.Rule
import org.junit.Test

class TrustbadgeUncertifiedExpandedAndroidTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val isCI = InstrumentationRegistry.getArguments().getString("CI").toBoolean()
    private val goldenName = GoldenTrustbadgeUncertifiedExpanded.raw + if (isCI) "-ci" else ""

    private fun showContent() {
        composeTestRule.setContent {

            val state = rememberTrustbadgeState()

            TrustbadgeTheme {
                Column {
                    Trustbadge(
                        state = state,
                        badgeContext = TrustbadgeContext.ShopGrade,
                        tsid = "X330A2E7D449E31E467D2F53A55DDD070",
                        channelId = "chl-bcd573bb-de56-45d6-966a-b46d63be4a1b"
                    )
                }
            }

            // expand the widget
            state.expand()
        }
    }

    @Test
    fun generateTrustbadgeViewScreenshots() {

        // arrange

        // act
        showContent()
        // wait for expand animation to finish
        composeTestRule.mainClock.advanceTimeBy(5000)
        composeTestRule.waitForIdle()
        val sut = composeTestRule.onNodeWithTag(TestTags.Trustbadge.raw)
        val bmp = sut.captureToImage().asAndroidBitmap()
        saveScreenshot(goldenName, bmp)

        // assert
        sut.assertExists()

    }

    @Test
    fun testTrustbadgeViewUncertifiedDefaultShownCorrectly() {

        // arrange

        // act
        showContent()
        // wait for expand animation to finish
        composeTestRule.mainClock.advanceTimeBy(5000)
        composeTestRule.waitForIdle()
        val sut = composeTestRule.onNodeWithTag(TestTags.Trustbadge.raw)

        // assert
        sut.assertExists()
        assertScreenshotMatchesGolden(goldenName, sut)
    }
}