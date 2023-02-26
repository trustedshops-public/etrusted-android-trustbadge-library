/*
 * Created by Ali Kabiri on 30.1.2023.
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

package com.etrusted.android.trustbadge.library.extensions

import android.content.Context
import java.io.File

@Throws(Exception::class)
fun Context.readJsonFile(jsonFilePath: String) : String {
    val assetInSt = this.assets.open(jsonFilePath)
    val fileContents = assetInSt.bufferedReader().use { it.readText() }
    assetInSt.close()
    return fileContents
}

/**
 * Returns a [File] with a path to additional test output directory of the emulator
 * everything stored under this directory will be available in the build folder under:
 * `build/outputs/managed_device_android_test_additional_output`
 * Currently used with Gradle Managed devices.
 */
fun Context.getAdditionalTestOutputDir(): File {
    return File(
        this.externalMediaDirs.first(), "additional_test_output").apply {
        mkdir()
    }
}

fun Context.getScreenshotsDir(): File {
    return File(
        this.getAdditionalTestOutputDir(), "screenshots").apply {
        mkdir()
    }
}