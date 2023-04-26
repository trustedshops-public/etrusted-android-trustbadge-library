/*
 * Created by Ali Kabiri on 13.3.2023.
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

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.etrusted.android.trustbadge.library.ILibrary
import com.etrusted.android.trustbadge.library.data.datasource.IAuthenticationDatasource
import com.etrusted.android.trustbadge.library.data.datasource.IProductGradeDatasource
import com.etrusted.android.trustbadge.library.data.datasource.IShopGradeDetailDatasource
import com.etrusted.android.trustbadge.library.data.datasource.ITrustbadgeDatasource
import com.etrusted.android.trustbadge.library.data.repository.IChannelInfoRepository
import com.etrusted.android.trustbadge.library.data.repository.IProductGradeRepository
import com.etrusted.android.trustbadge.library.data.repository.ITrustbadgeRepository
import com.etrusted.android.trustbadge.library.domain.IChannelInfoDataUseCase
import com.etrusted.android.trustbadge.library.domain.IGetProductGradeUseCase
import com.etrusted.android.trustbadge.library.domain.IGuaranteeUseCase
import com.etrusted.android.trustbadge.library.domain.ITrustbadgeDataUseCase
import com.etrusted.android.trustbadge.library.model.*
import com.etrusted.android.trustbadge.library.model.ChannelInfo.AggregateRating
import com.etrusted.android.trustbadge.library.ui.badge.ITrustbadgeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*

internal fun getUrlsFor(endpoint: String): IUrls {
    return object: IUrls {
        override fun authenticationUrl(env: EnvironmentKey): String = endpoint
        override fun trustbadgeJsonUrl(env: EnvironmentKey): String = endpoint
        override fun channelAggregateRatingUrl(env: EnvironmentKey): String = endpoint
        override fun productGradeJsonUrl(env: EnvironmentKey): String = endpoint
    }
}

internal fun getFakeLibrary(): ILibrary {
    return object: ILibrary {
        override var config = TrustbadgeConfig("fakeId", "fakeSecret")
        override fun configure(context: Context): ILibrary { return this }
    }
}

internal fun getFakeCertificate(): String {
    return InstrumentationRegistry.getInstrumentation().context.readJsonFile(
        "certificates/instrumentation_cert.pem")
}

internal fun getFakeTrustbadgeData(
    rating: Float? = null,
    maxProtectionAmount: String = "2,500"
): TrustbadgeData {
    val fakeString = "fakeString"
    return TrustbadgeData(shop = TrustbadgeData.Shop(
        tsid = fakeString,
        url = fakeString,
        name = fakeString,
        languageISO2 = fakeString,
        targetMarketISO3 = fakeString,
        trustMark = TrustbadgeData.Shop.TrustMark(
            status = fakeString,
            validTo = fakeString,
            validFrom = fakeString,
        ),
        guarantee = TrustbadgeData.Shop.Guarantee(
            mainProtectionCurrency = fakeString,
            maxProtectionAmount = maxProtectionAmount,
            maxProtectionDuration = fakeString,
        ),
        rating = rating,
    ))
}

internal fun getFakeGuarantee(
    maxProtectionAmount: String = "2,500"
): TrustbadgeData.Shop.Guarantee {
    val fakeString = "fakeString"
    return TrustbadgeData.Shop.Guarantee(
        mainProtectionCurrency = fakeString,
        maxProtectionAmount = maxProtectionAmount,
        maxProtectionDuration = fakeString,
    )
}

internal fun getFakeAuthToken(): AuthenticationToken {
    val fakeString = "fakeString"
    val fakeInt = 123
    val fakeDate = Date()
    return AuthenticationToken(
        accessToken = fakeString,
        expiresIn = fakeInt,
        refreshExpiresIn = fakeInt,
        tokenType = fakeString,
        scope = fakeString,
        notBeforePolicy = fakeInt,
        latestAuthenticationTimestamp = fakeDate,
    )
}

internal fun getFakeChannelInfo(fakeRating: Float = 3.51f): ChannelInfo {
    val fakeDate = Date()
    return ChannelInfo(
        week=AggregateRating(count=0, rating=fakeRating, distribution=null, period=null),
        month=AggregateRating(count=0, rating=fakeRating, distribution=null, period=null),
        quarter=AggregateRating(count=2, rating=fakeRating, distribution=null,
            period=AggregateRating.AggregateRatingPeriod(
                start = fakeDate,
                end = fakeDate,
                firstConsideredReviewSubmission = fakeDate,
                lastConsideredReviewSubmission = fakeDate,
                calculatedAt = fakeDate,
                ratingTrend = AggregateRating.AggregateRatingPeriod.RatingTrend.NEUTRAL
        )),
        year=AggregateRating(count=5, rating=fakeRating, distribution=null,
            period=AggregateRating.AggregateRatingPeriod(
                start = fakeDate,
                end = fakeDate,
                firstConsideredReviewSubmission = fakeDate,
                lastConsideredReviewSubmission = fakeDate,
                calculatedAt = fakeDate,
                ratingTrend = AggregateRating.AggregateRatingPeriod.RatingTrend.POSITIVE
        )),
        overall=AggregateRating(
            count=70,
            rating=fakeRating,
            distribution=AggregateRating.AggregateRatingDistribution(
                oneStar = 1,
                twoStars = 1,
                threeStars = 1,
                fourStars = 1,
                fiveStars = 1,
            ),
            period=AggregateRating.AggregateRatingPeriod(
                start = fakeDate,
                end = fakeDate,
                firstConsideredReviewSubmission = fakeDate,
                lastConsideredReviewSubmission = fakeDate,
                calculatedAt = fakeDate,
                ratingTrend = AggregateRating.AggregateRatingPeriod.RatingTrend.NEGATIVE
            ))
    )
}
internal fun getFakeProductGrade(fakeRating: Float = 3.51f): ProductGrade {
    val fakeDate = Date()
    return ProductGrade(
        year=ProductGrade.AggregateRating(count=5, rating=fakeRating, distribution=null,
            period=ProductGrade.AggregateRating.AggregateRatingPeriod(
                start = fakeDate,
                end = fakeDate,
                firstConsideredReviewSubmission = fakeDate,
                lastConsideredReviewSubmission = fakeDate,
                calculatedAt = fakeDate,
                ratingTrend = ProductGrade.AggregateRating.AggregateRatingPeriod.RatingTrend.POSITIVE
        )),
    )
}

internal fun getFakeAuthDatasource(
    result: Result<AuthenticationToken> = Result.success(getFakeAuthToken())
): IAuthenticationDatasource {
    return object : IAuthenticationDatasource {
        override suspend fun getAccessTokenUsingSecret(): Result<AuthenticationToken> = result
    }
}

internal fun getFakeTrustbadgeDatasource(
    result: Result<TrustbadgeData> = Result.success(getFakeTrustbadgeData())
): ITrustbadgeDatasource {
    return object : ITrustbadgeDatasource {
        override suspend fun fetchTrustbadge(tsid: String): Result<TrustbadgeData> = result
    }
}

internal fun getFakeShopGradeDetailDatasource(
    result: Result<ChannelInfo> = Result.success(getFakeChannelInfo())
): IShopGradeDetailDatasource {
    return object : IShopGradeDetailDatasource {
        override suspend fun fetchShopGradeDetail(channelId: String, accessToken: String):
                Result<ChannelInfo> = result
    }
}

internal fun getFakeProductGradeDetailDatasource(
    result: Result<ProductGrade> = Result.success(getFakeProductGrade()),
    spyHexSku: (String) -> Unit = {},
): IProductGradeDatasource {
    return object : IProductGradeDatasource {
        override suspend fun fetchProductGrade(channelId: String, hexSku: String):
                Result<ProductGrade> {
            spyHexSku(hexSku)
            return result
        }
    }
}

internal fun getFakeTrustbadgeRepository(
    result: Result<TrustbadgeData> = Result.success(getFakeTrustbadgeData())
): ITrustbadgeRepository {
    return object : ITrustbadgeRepository {

        override suspend fun fetchTrustbadgeData(
            tsid: String,
            channelId: String,
            channelInfo: ChannelInfo?
        ): Result<TrustbadgeData> {
            return result
        }
    }
}

internal fun getFakeChannelInfoRepository(
    result: Result<ChannelInfo> = Result.success(getFakeChannelInfo())
): IChannelInfoRepository {
    return object : IChannelInfoRepository {
        override suspend fun fetchChannelInfo(channelId: String): Result<ChannelInfo> = result
    }
}
internal fun getFakeProductGradeRepository(
    result: Result<ProductGrade> = Result.success(getFakeProductGrade())
): IProductGradeRepository {
    return object : IProductGradeRepository {
        override suspend fun fetchProductGrade(channelId: String, sku: String): Result<ProductGrade> = result
    }
}

internal fun getFakeTrustbadgeDataUseCase(
    result: Result<TrustbadgeData> = Result.success(getFakeTrustbadgeData())
): ITrustbadgeDataUseCase {
    return object : ITrustbadgeDataUseCase {
        override suspend fun invoke(channelId: String, tsid: String): Result<TrustbadgeData> =
            result
    }
}

internal fun getFakeGuaranteeUseCase(
    result: Result<TrustbadgeData.Shop.Guarantee> = Result.success(getFakeGuarantee())
): IGuaranteeUseCase {
    return object : IGuaranteeUseCase {
        override suspend fun invoke(channelId: String, tsid: String):
                Result<TrustbadgeData.Shop.Guarantee> = result
    }
}

internal fun getFakeProductGradeUseCase(
    result: Result<ProductGrade> = Result.success(getFakeProductGrade())
): IGetProductGradeUseCase {
    return object : IGetProductGradeUseCase {
        override suspend fun invoke(channelId: String, sku: String): Result<ProductGrade> = result
    }
}

internal fun getFakeChannelInfoDataUseCase(
    result: Result<ChannelInfo> = Result.success(getFakeChannelInfo())
): IChannelInfoDataUseCase {
    return object: IChannelInfoDataUseCase {
        override suspend fun invoke(channelId: String): Result<ChannelInfo> = result
    }
}

internal fun getFakeTrustbadgeViewModel(
    trustbadgeData: StateFlow<TrustbadgeData?> = MutableStateFlow(getFakeTrustbadgeData()),
    guarantee: StateFlow<TrustbadgeData.Shop.Guarantee?> = MutableStateFlow(getFakeGuarantee()),
    productGrade: StateFlow<ProductGrade> = MutableStateFlow(getFakeProductGrade())
): ITrustbadgeViewModel {
    return object : ITrustbadgeViewModel {
        override val trustbadgeData: StateFlow<TrustbadgeData?> = trustbadgeData
        override val guarantee: StateFlow<TrustbadgeData.Shop.Guarantee?> = guarantee
        override val productGrade: StateFlow<ProductGrade?> = productGrade
        override fun fetchTrustbadgeData(tsId: String, channelId: String) {}
        override fun fetchGuarantee(tsId: String, channelId: String) {}
        override fun fetchProductGrade(channelId: String, sku: String) {}
    }
}