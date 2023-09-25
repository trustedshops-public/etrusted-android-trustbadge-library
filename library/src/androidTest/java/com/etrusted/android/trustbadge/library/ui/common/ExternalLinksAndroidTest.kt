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

package com.etrusted.android.trustbadge.library.ui.common

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import org.junit.Test

private class TestContextWrapper(base: Context) : ContextWrapper(base) {
    var isStartActivityCalled: Boolean = false
        private set

    override fun startActivity(intent: Intent?) {
        isStartActivityCalled = true
    }
}
internal class ExternalLinksAndroidTest {

    @Test
    fun openLinkInExternalBrowserStartsAnActivityToOpenTheLinkInExternalBrowser() {

        // arrange
        val baseContext = InstrumentationRegistry.getInstrumentation().targetContext
        val testContext = TestContextWrapper(baseContext)
        val sut = ::openLinkInExternalBrowser

        // act
        sut(testContext, "https://www.etrusted.com")

        // assert
        assertThat(testContext.isStartActivityCalled).isTrue()
    }
}