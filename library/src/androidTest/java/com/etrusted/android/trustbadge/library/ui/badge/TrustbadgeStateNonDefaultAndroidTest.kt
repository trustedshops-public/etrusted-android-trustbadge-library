/*
 * Created by Ali Kabiri on 25.2.2023.
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

package com.etrusted.android.trustbadge.library.ui.badge

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithTag
import com.etrusted.android.trustbadge.library.common.internal.GoldenNames
import com.etrusted.android.trustbadge.library.common.internal.TestTags
import com.etrusted.android.trustbadge.library.common.internal.assertScreenshotMatchesGolden
import com.etrusted.android.trustbadge.library.common.internal.getFakeTrustbadgeData
import com.etrusted.android.trustbadge.library.common.internal.getFakeTrustbadgeViewModel
import com.etrusted.android.trustbadge.library.common.internal.saveScreenshot
import com.etrusted.android.trustbadge.library.ui.theme.TrustbadgeTheme
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Ignore
import org.junit.Test

/**
 * Start the test in expanded state.
 * Make sure calling hide() on the state retracts the widget to hide it.
 */
internal class TrustbadgeStateNonDefaultAndroidTest: TrustbadgeAndroidTest() {

    override val goldenName = GoldenNames.GoldenTrustbadgeUncertifiedExpandedNonDefaultState.raw +
            if (isCI) "-ci" else ""

    private val initialState = TrustbadgeStateValue.EXPANDED

    override fun showContent() {

        composeTestRule.setContent {

            val fakeViewModel = getFakeTrustbadgeViewModel(
                trustbadgeData = MutableStateFlow(getFakeTrustbadgeData(rating = 4.5f))
            )
            val state = rememberTrustbadgeState(
                initialState = initialState,
            )

            TrustbadgeTheme {
                Column {
                    TrustbadgeContent(
                        modifier = Modifier,
                        viewModel = fakeViewModel,
                        state = state,
                        badgeContext = TrustbadgeContext.ShopGrade,
                        tsid = "X330A2E7D449E31E467D2F53A55DDD070",
                        channelId = "chl-bcd573bb-de56-45d6-966a-b46d63be4a1b"
                    )
                }
            }
        }
    }

//    @Ignore("activate to generate fresh screenshots")
    @Test
    override fun generateScreenshot() {

        // arrange
        showContent()

        // act
        val sut = composeTestRule.onNodeWithTag(TestTags.Trustbadge.raw)
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
        val sut = composeTestRule.onNodeWithTag(TestTags.Trustbadge.raw)

        // assert
        sut.assertExists()
        assertScreenshotMatchesGolden(goldenName, sut)
    }
}