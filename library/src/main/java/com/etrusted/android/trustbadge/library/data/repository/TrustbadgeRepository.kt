/*
 * Created by Ali Kabiri on 30.1.2023.
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

package com.etrusted.android.trustbadge.library.data.repository

import com.etrusted.android.trustbadge.library.data.datasource.*
import com.etrusted.android.trustbadge.library.data.datasource.AuthenticationDatasource
import com.etrusted.android.trustbadge.library.data.datasource.IAuthenticationDatasource
import com.etrusted.android.trustbadge.library.data.datasource.IShopGradeDetailDatasource
import com.etrusted.android.trustbadge.library.data.datasource.ShopGradeDetailDatasource
import com.etrusted.android.trustbadge.library.data.datasource.TrustbadgeDatasource
import com.etrusted.android.trustbadge.library.model.TrustbadgeData
import com.etrusted.android.trustbadge.library.model.enrichTrustbadgeDataWithInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal interface ITrustbadgeRepository {
    suspend fun fetchTrustbadgeData(tsid: String, channelId: String): Result<TrustbadgeData>
}

/**
 * This class aims on reading the Trustbadge data
 * without using any third party library (e.g. Okhttp, Retrofit, etc...)
 */
internal class TrustbadgeRepository
constructor(
    private val auth: IAuthenticationDatasource = AuthenticationDatasource(),
    private val trustbadgeDatasource: ITrustbadgeDatasource = TrustbadgeDatasource(),
    private val shopGradeDetailDatasource: IShopGradeDetailDatasource = ShopGradeDetailDatasource(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
): ITrustbadgeRepository {

    @Throws
    override suspend fun fetchTrustbadgeData(
        tsid: String,
        channelId: String,
    ): Result<TrustbadgeData> {

        return withContext(dispatcher) {

            val token = auth.getAccessTokenUsingSecret().getOrThrow()
            val channelInfoData = shopGradeDetailDatasource.fetchShopGradeDetail(
                    channelId = channelId,
                    accessToken = token.accessToken).getOrThrow()
            val tBadgeData = trustbadgeDatasource.fetchTrustbadge(tsid).getOrThrow()
            val tBadgeWithRating = channelInfoData.enrichTrustbadgeDataWithInfo(tBadgeData)

            Result.success(tBadgeWithRating)
        }
    }
}