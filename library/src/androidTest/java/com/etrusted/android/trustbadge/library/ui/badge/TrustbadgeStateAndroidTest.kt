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

@file:OptIn(ExperimentalCoroutinesApi::class)

package com.etrusted.android.trustbadge.library.ui.badge

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.etrusted.android.trustbadge.library.common.internal.TestTags
import com.etrusted.android.trustbadge.library.ui.theme.TrustbadgeTheme
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

/**
 * Start the test in expanded state.
 * Make sure calling hide() on the state retracts the widget to hide it.
 */
internal class TrustbadgeStateAndroidTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun showContent() {

        composeTestRule.setContent {

            val state = rememberTrustbadgeState()

            TrustbadgeTheme {
                Column {
                    Trustbadge(
                        state = state,
                        badgeContext = TrustbadgeContext.BuyerProtection(),
                        tsid = "X330A2E7D449E31E467D2F53A55DDD070",
                        channelId = "chl-bcd573bb-de56-45d6-966a-b46d63be4a1b"
                    )
                }
            }

            LaunchedEffect(Unit) {
                state.hide()
            }
        }
    }

    @Test
    fun testHideRetractsTheWidgetToHide() {

        // arrange
        showContent()
        composeTestRule.waitForIdle() // wait for hide

        // act
        val sut = composeTestRule.onNodeWithTag(TestTags.Trustbadge.raw)

        // assert
        sut.assertDoesNotExist()
    }

    @Test
    fun testExpandWorksFromInvisible() = runTest {

        // arrange
        val sut = TrustbadgeState(currentState = TrustbadgeStateValue.INVISIBLE)

        // act
        sut.expand()

        // assert
        assertThat(sut.currentState).isEqualTo(TrustbadgeStateValue.DEFAULT)
    }

    @Test
    fun testExpandWorksFromDefault() = runTest {

        // arrange
        val sut = TrustbadgeState()

        // act
        sut.expand()

        // assert
        assertThat(sut.currentState).isEqualTo(TrustbadgeStateValue.EXPANDED)
    }

    @Test
    fun testExpandWorksFromExpanded() = runTest {

        // arrange
        val sut = TrustbadgeState(currentState = TrustbadgeStateValue.EXPANDED)

        // act
        sut.expand()

        // assert
        assertThat(sut.currentState).isEqualTo(TrustbadgeStateValue.EXPANDED)
    }

    @Test
    fun testExpandWorksFromExpandedAsPopup() = runTest {

        // arrange
        val sut = TrustbadgeState(currentState = TrustbadgeStateValue.EXPANDED_AS_CARD)

        // act
        sut.expand()

        // assert
        assertThat(sut.currentState).isEqualTo(TrustbadgeStateValue.EXPANDED)
    }

    @Test
    fun testRetractWorksFromInvisible() = runTest {

        // arrange
        val sut = TrustbadgeState(currentState = TrustbadgeStateValue.INVISIBLE)

        // act
        sut.retract()

        // assert
        assertThat(sut.currentState).isEqualTo(TrustbadgeStateValue.INVISIBLE)
    }

    @Test
    fun testRetractWorksFromDefault() = runTest {

        // arrange
        val sut = TrustbadgeState()

        // act
        sut.retract()

        // assert
        assertThat(sut.currentState).isEqualTo(TrustbadgeStateValue.INVISIBLE)
    }

    @Test
    fun testRetractWorksFromExpanded() = runTest {

        // arrange
        val sut = TrustbadgeState(currentState = TrustbadgeStateValue.EXPANDED)

        // act
        sut.retract()

        // assert
        assertThat(sut.currentState).isEqualTo(TrustbadgeStateValue.DEFAULT)
    }

    @Test
    fun testRetractWorksFromExpandedAsPopup() = runTest {

        // arrange
        val sut = TrustbadgeState(currentState = TrustbadgeStateValue.EXPANDED_AS_CARD)

        // act
        sut.retract()

        // assert
        assertThat(sut.currentState).isEqualTo(TrustbadgeStateValue.EXPANDED)
    }

    @Test
    fun testShowWorksFromDefault() = runTest {

        // arrange
        val sut = TrustbadgeState()

        // act
        sut.show()

        // assert
        assertThat(sut.currentState).isEqualTo(TrustbadgeStateValue.DEFAULT)
    }

    @Test
    fun testShowWorksFromInvisible() = runTest {

        // arrange
        val sut = TrustbadgeState(currentState = TrustbadgeStateValue.INVISIBLE)

        // act
        sut.show()

        // assert
        assertThat(sut.currentState).isEqualTo(TrustbadgeStateValue.DEFAULT)
    }

    @Test
    fun testHideWorksFromInvisible() = runTest {

        // arrange
        val sut = TrustbadgeState(currentState = TrustbadgeStateValue.INVISIBLE)

        // act
        sut.hide()

        // assert
        assertThat(sut.currentState).isEqualTo(TrustbadgeStateValue.INVISIBLE)
    }

    @Test
    fun testHideWorksFromDefault() = runTest {

        // arrange
        val sut = TrustbadgeState()

        // act
        sut.hide()

        // assert
        assertThat(sut.currentState).isEqualTo(TrustbadgeStateValue.INVISIBLE)
    }

    @Test
    fun testHideWorksFromExpanded() = runTest {

        // arrange
        val sut = TrustbadgeState(currentState = TrustbadgeStateValue.EXPANDED)

        // act
        sut.hide()

        // assert
        assertThat(sut.currentState).isEqualTo(TrustbadgeStateValue.INVISIBLE)
    }

    @Test
    fun testHideWorksFromExpandedAsPopup() = runTest {

        // arrange
        val sut = TrustbadgeState(currentState = TrustbadgeStateValue.EXPANDED_AS_CARD)

        // act
        sut.hide()

        // assert
        assertThat(sut.currentState).isEqualTo(TrustbadgeStateValue.INVISIBLE)
    }
}