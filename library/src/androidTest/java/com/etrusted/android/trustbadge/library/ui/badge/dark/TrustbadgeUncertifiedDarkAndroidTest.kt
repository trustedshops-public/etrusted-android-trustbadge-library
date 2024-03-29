/*
 * Created by Ali Kabiri on 26.6.2023.
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

package com.etrusted.android.trustbadge.library.ui.badge.dark

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.tooling.preview.UiMode
import com.etrusted.android.trustbadge.library.common.internal.GoldenNames
import com.etrusted.android.trustbadge.library.common.internal.assertScreenshotMatchesGolden
import com.etrusted.android.trustbadge.library.common.internal.getFakeTrustbadgeData
import com.etrusted.android.trustbadge.library.common.internal.getFakeTrustbadgeViewModel
import com.etrusted.android.trustbadge.library.common.internal.saveScreenshot
import com.etrusted.android.trustbadge.library.ui.badge.TrustbadgeAndroidTest
import com.etrusted.android.trustbadge.library.ui.badge.TrustbadgeContent
import com.etrusted.android.trustbadge.library.ui.badge.TrustbadgeContext
import com.etrusted.android.trustbadge.library.ui.badge.rememberTrustbadgeState
import com.etrusted.android.trustbadge.library.ui.theme.TrustbadgeTheme
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Ignore
import org.junit.Test

internal class TrustbadgeUncertifiedDarkAndroidTest: TrustbadgeAndroidTest() {
    override val goldenName: String
        get() = GoldenNames.GoldenTrustbadgeUncertifiedDark.raw + if (isCI) "-ci" else ""

    private val tagTrustbadgeCollectionColumn = "tag_trustbadge_collection_column"

    override fun showContent() {
        composeTestRule.setContent {
            val fakeViewModel = getFakeTrustbadgeViewModel(
                trustbadgeData = MutableStateFlow(getFakeTrustbadgeData(rating = 4.5f))
            )
            val state = rememberTrustbadgeState()

            TrustbadgeTheme(darkTheme = true) {
                Column(Modifier.testTag(tagTrustbadgeCollectionColumn)) {
                    TrustbadgeContent(
                        modifier = Modifier,
                        viewModel = fakeViewModel,
                        state = rememberTrustbadgeState(),
                        badgeContext = TrustbadgeContext.TrustMark,
                        tsid = "X330A2E7D449E31E467D2F53A55DDD070",
                        channelId = "chl-bcd573bb-de56-45d6-966a-b46d63be4a1b"
                    )
                    TrustbadgeContent(
                        modifier = Modifier,
                        viewModel = fakeViewModel,
                        state = state,
                        badgeContext = TrustbadgeContext.ShopGrade,
                        tsid = "X330A2E7D449E31E467D2F53A55DDD070",
                        channelId = "chl-bcd573bb-de56-45d6-966a-b46d63be4a1b"
                    )
                    TrustbadgeContent(
                        modifier = Modifier,
                        viewModel = fakeViewModel,
                        state = state,
                        badgeContext = TrustbadgeContext.ProductGrade(sku = "fakeSKU"),
                        tsid = "X330A2E7D449E31E467D2F53A55DDD070",
                        channelId = "chl-bcd573bb-de56-45d6-966a-b46d63be4a1b"
                    )
                    TrustbadgeContent(
                        modifier = Modifier,
                        viewModel = fakeViewModel,
                        state = state,
                        badgeContext = TrustbadgeContext.BuyerProtection(),
                        tsid = "X330A2E7D449E31E467D2F53A55DDD070",
                        channelId = "chl-bcd573bb-de56-45d6-966a-b46d63be4a1b"
                    )
                }
            }

            // expand the widget
            state.expand()
        }
    }

    @Ignore("activate to generate fresh screenshots")
    @Test
    override fun generateScreenshot() {
        // arrange
        showContent()

        // act
        composeTestRule.mainClock.advanceTimeBy(5000) // wait to finish expand animation
        composeTestRule.waitForIdle()
        val sut = composeTestRule.onNodeWithTag(tagTrustbadgeCollectionColumn)
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
        composeTestRule.mainClock.advanceTimeBy(10000) // wait to finish expand animation
        composeTestRule.waitForIdle()
        val sut = composeTestRule.onNodeWithTag(tagTrustbadgeCollectionColumn).performClick()

        // assert
        sut.assertExists()
        assertScreenshotMatchesGolden(goldenName, sut)
    }

}