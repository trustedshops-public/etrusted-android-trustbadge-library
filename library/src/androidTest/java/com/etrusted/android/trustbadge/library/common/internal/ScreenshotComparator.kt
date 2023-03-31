/*
 * Created by Ali Kabiri on 27.2.2023.
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

package com.etrusted.android.trustbadge.library.common.internal

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.captureToImage
import androidx.test.platform.app.InstrumentationRegistry
import java.io.FileOutputStream

/**
 * On-device screenshot comparator that uses golden images present in
 * `androidTest/assets/screenshots`.
 *
 * Minimum SDK is O. Densities between devices must match.
 *
 */
@RequiresApi(Build.VERSION_CODES.O)
internal fun assertScreenshotMatchesGolden(
    goldenName: String,
    node: SemanticsNodeInteraction
) {
    val bitmap = node.captureToImage().asAndroidBitmap()

    // Save screenshot to file for debugging
    saveScreenshot(goldenName + System.currentTimeMillis().toString(), bitmap)
    val golden = InstrumentationRegistry.getInstrumentation()
        .context.resources.assets.open("screenshots/$goldenName.png")
        .use { BitmapFactory.decodeStream(it) }

    golden.compare(bitmap)
}

internal fun saveScreenshot(filename: String, bmp: Bitmap) {
    val path = InstrumentationRegistry.getInstrumentation().context.getScreenshotsDir()
    FileOutputStream("$path/$filename.png").use { out ->
        bmp.compress(Bitmap.CompressFormat.PNG, 85, out)
    }
    println("Saved screenshot to $path/$filename.png")
}

private fun Bitmap.compare(other: Bitmap) {
    if (this.width != other.width || this.height != other.height) {
        throw AssertionError("Size of screenshot does not match golden file (check device density)")
    }
    // Compare row by row to save memory on device
    val row1 = IntArray(width)
    val row2 = IntArray(width)
    for (column in 0 until height) {
        // Read one row per bitmap and compare
        this.getRow(row1, column)
        other.getRow(row2, column)
        if (!row1.contentEquals(row2)) {
            throw AssertionError("Sizes match but bitmap content has differences")
        }
    }
}

private fun Bitmap.getRow(pixels: IntArray, column: Int) {
    this.getPixels(pixels, 0, width, 0, column, width, 1)
}