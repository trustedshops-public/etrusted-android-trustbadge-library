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
import com.etrusted.android.trustbadge.library.data.repository.ITrustbadgeRepository
import com.etrusted.android.trustbadge.library.domain.ITrustbadgeDataUseCase
import com.etrusted.android.trustbadge.library.model.AuthenticationToken
import com.etrusted.android.trustbadge.library.model.ChannelInfo
import com.etrusted.android.trustbadge.library.model.ChannelInfo.AggregateRating
import com.etrusted.android.trustbadge.library.model.TrustbadgeConfig
import com.etrusted.android.trustbadge.library.model.TrustbadgeData
import java.util.*

internal fun getUrlsFor(endpoint: String): IUrls {
    return object: IUrls {
        override fun authenticationUrl(env: EnvironmentKey): String = endpoint
        override fun trustbadgeJsonUrl(env: EnvironmentKey): String = endpoint
        override fun channelAggregateRatingUrl(env: EnvironmentKey): String = endpoint
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

internal fun getFakeTrustbadgeData(): TrustbadgeData {
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
            maxProtectionAmount = fakeString,
            maxProtectionDuration = fakeString,
        )
    ))
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

internal fun getFakeChannelInfo(): ChannelInfo {
    val fakeDate = Date()
    return ChannelInfo(
        week=AggregateRating(count=0, rating=0f, distribution=null, period=null),
        month=AggregateRating(count=0, rating=0f, distribution=null, period=null),
        quarter=AggregateRating(count=2, rating=3.5f, distribution=null,
            period=AggregateRating.AggregateRatingPeriod(
                start = fakeDate,
                end = fakeDate,
                firstConsideredReviewSubmission = fakeDate,
                lastConsideredReviewSubmission = fakeDate,
                calculatedAt = fakeDate,
                ratingTrend = AggregateRating.AggregateRatingPeriod.RatingTrend.NEUTRAL
        )),
        year=AggregateRating(count=5, rating=4.4f, distribution=null,
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
            rating=3.51f,
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

internal fun getFakeTrustbadgeRepository(
    result: Result<TrustbadgeData> = Result.success(getFakeTrustbadgeData())
): ITrustbadgeRepository {
    return object : ITrustbadgeRepository {
        override suspend fun fetchTrustbadgeData(
            tsid: String, channelId: String
        ): Result<TrustbadgeData> {
            return result
        }
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