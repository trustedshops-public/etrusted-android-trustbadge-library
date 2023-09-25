/*
 * Created by Ali Kabiri on 25.09.2023.
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

package com.etrusted.android.trustbadge.library.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.etrusted.android.trustbadge.library.common.internal.TestTags
import com.etrusted.android.trustbadge.library.ui.theme.TrustbadgeTheme
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class ActionButtonAndroidTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var isClicked = false

    @Before
    fun setup() {
        isClicked = false
    }

    @Test
    fun testClickOnButtonWorks() {

        // arrange
        composeTestRule.setContent {
            ActionButton(
                onClick = {
                    isClicked = true
                },
                content = {},
            )
        }

        // act
        val sut = composeTestRule.onNodeWithTag(TestTags.ButtonAction.raw)
        sut.performClick()

        // assert
        sut.assertExists()
        assertThat(isClicked).isTrue()
    }

    @Test
    fun testClickOnButtonWorksWithoutDefaultModifier() {

        // arrange
        composeTestRule.setContent {
            TrustbadgeTheme(darkTheme = true) {
                ActionButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        isClicked = true
                    },
                    content = {
                        Text(text = "Test")
                    },
                )
            }
        }

        // act
        val sut = composeTestRule.onNodeWithTag(TestTags.ButtonAction.raw)
        sut.performClick()

        // assert
        sut.assertExists()
        assertThat(isClicked).isTrue()
    }
}