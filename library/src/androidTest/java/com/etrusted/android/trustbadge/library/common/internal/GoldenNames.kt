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

/**
 * A golden file is an expected output file
 * The GoldenNames are the expected file names for screenshots stored in the assets folder.
 *
 *  @param raw the file name of an expected screenshot
 */
internal sealed class GoldenNames(val raw: String) {
    object GoldenTrustbadgeUncertifiedDefault: GoldenNames("screenshot-trustbadge-uncertified-default")
    object GoldenTrustbadgeUncertifiedDefaultIntegration: GoldenNames("screenshot-trustbadge-uncertified-default-integration")
    object GoldenTrustbadgeUncertifiedExpanded: GoldenNames("screenshot-trustbadge-uncertified-expanded")
    object GoldenTrustbadgeUncertifiedExpandedIntegration: GoldenNames("screenshot-trustbadge-uncertified-expanded-integration")
    object GoldenTrustbadgeUncertifiedExpandedBuyerProtection: GoldenNames("screenshot-trustbadge-uncertified-expanded-buyer-protection")
    object GoldenTrustbadgeUncertifiedExpandedBuyerProtectionIntegration: GoldenNames("screenshot-trustbadge-uncertified-expanded-buyer-protection-integration")
    object GoldenTrustbadgeUncertifiedExpandedProductGrade: GoldenNames("screenshot-trustbadge-uncertified-expanded-product-grade")
    object GoldenTrustbadgeUncertifiedExpandedProductGradeIntegration: GoldenNames("screenshot-trustbadge-uncertified-expanded-product-grade-integration")

    object GoldenTrustbadgeUncertifiedDark: GoldenNames("screenshot-trustbadge-uncertified-dark")

    object GoldenTrustbadgeViewRounded: GoldenNames("screenshot-trustbadge-view-rounded")

    // image collection
    object GoldenImageCircleGeneric: GoldenNames("screenshot-image-circle-generic")
    object GoldenImageCircleGenericFromUrl: GoldenNames("screenshot-circle-generic-from-url")
}