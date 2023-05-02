/*
 * Created by Ali Kabiri on 2.5.2023.
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

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import coil.Coil
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.test.FakeImageLoaderEngine
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoilApi::class)
internal abstract class ImageCollectionAndroidTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Even using gradle managed devices might not be sufficient for having consistent screenshot,
     * therefore, checking if the tests are running on the CI to use correct assets.
     */
    internal val isCI = InstrumentationRegistry.getArguments().getString("CI").toBoolean()

    internal abstract val goldenName: String

    internal val fakeImageUrl = "doesn't matter"

    internal abstract fun showContent()

    @Before
    internal fun prepare() {
        // prepare coil for testing
        val engine = FakeImageLoaderEngine.Builder()
            .intercept(fakeImageUrl, ColorDrawable(Color.RED))
            .build()
        val imageLoader = ImageLoader.Builder(InstrumentationRegistry.getInstrumentation().context)
            .components { add(engine) }
            .build()
        Coil.setImageLoader(imageLoader)
    }

    @Test
    internal abstract fun generateScreenshot()

    @Test
    internal abstract fun testScreenshotMatchesGolden()
}