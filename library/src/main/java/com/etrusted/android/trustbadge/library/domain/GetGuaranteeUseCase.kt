/*
 * Created by Ali Kabiri on 27.3.2023.
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

package com.etrusted.android.trustbadge.library.domain

import com.etrusted.android.trustbadge.library.model.TrustbadgeData

internal interface IGuaranteeUseCase {
    suspend operator fun invoke(channelId: String, tsid: String):
            Result<TrustbadgeData.Shop.Guarantee>
}

internal class GetGuaranteeUseCase(
    private val getTrustbadgeDataUseCase: ITrustbadgeDataUseCase = GetTrustbadgeDataUseCase(),
): IGuaranteeUseCase {
    override suspend fun invoke(
        channelId: String,
        tsid: String
    ): Result<TrustbadgeData.Shop.Guarantee> = getTrustbadgeDataUseCase(channelId, tsid)
        .map { it.shop.guarantee }
}