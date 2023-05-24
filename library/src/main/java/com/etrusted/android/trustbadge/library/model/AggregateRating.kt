/*
 * Created by Ali Kabiri on 24.5.2023.
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

package com.etrusted.android.trustbadge.library.model

import com.etrusted.android.trustbadge.library.common.internal.fromString
import java.util.Date
import com.etrusted.android.trustbadge.library.model.AggregateRating.AggregateRatingPeriod
import com.etrusted.android.trustbadge.library.model.AggregateRating.AggregateRatingPeriod.RatingTrend
import com.etrusted.android.trustbadge.library.model.AggregateRating.AggregateRatingDistribution
import org.json.JSONObject


internal data class AggregateRating(
    val count: Int,
    val rating: Float,
    val distribution: AggregateRatingDistribution? = null,
    val period: AggregateRatingPeriod? = null,
) {
    data class AggregateRatingDistribution(
        val oneStar: Int?,
        val twoStars: Int?,
        val threeStars: Int?,
        val fourStars: Int?,
        val fiveStars: Int?
    ) {
        companion object {
            internal const val ONE_STAR = "oneStar"
            internal const val TWO_STAR = "twoStars"
            internal const val THREE_STAR = "threeStars"
            internal const val FOUR_STAR = "fourStars"
            internal const val FIVE_STAR = "fiveStars"
        }
    }
    data class AggregateRatingPeriod(
        val start: Date,
        val end: Date,
        val firstConsideredReviewSubmission: Date?,
        val lastConsideredReviewSubmission: Date?,
        val calculatedAt: Date?,
        val ratingTrend: RatingTrend?
    ) {
        enum class RatingTrend(val raw: String) {
            NEGATIVE("NEGATIVE"), NEUTRAL("NEUTRAL"), POSITIVE("POSITIVE");
            companion object {
                fun forRaw(raw: String): RatingTrend? =
                    values().firstOrNull { it.raw == raw  }
            }
        }
        companion object {
            internal const val START = "start"
            internal const val END = "end"
            internal const val FIRST_SUBMISSION = "firstConsideredReviewSubmission"
            internal const val LAST_SUBMISSION = "lastConsideredReviewSubmission"
            internal const val CALCULATED_AT = "calculatedAt"
            internal const val RATING_TREND = "ratingTrend"
        }
    }
}

internal fun AggregateRatingDistribution.Companion.fromJson(body: JSONObject):
        AggregateRatingDistribution {
    return AggregateRatingDistribution(
        oneStar = body.optInt(ONE_STAR),
        twoStars = body.optInt(TWO_STAR),
        threeStars = body.optInt(THREE_STAR),
        fourStars = body.optInt(FOUR_STAR),
        fiveStars = body.optInt(FIVE_STAR),
    )
}

internal fun AggregateRatingPeriod.Companion.fromJson(body: JSONObject): AggregateRatingPeriod {
    return AggregateRatingPeriod(
        start = Date().fromString(body.getString(START)) ?: Date(),
        end= Date().fromString(body.getString(END)) ?: Date(),
        firstConsideredReviewSubmission = Date().fromString(body.optString(FIRST_SUBMISSION)),
        lastConsideredReviewSubmission = Date().fromString(body.optString(LAST_SUBMISSION)),
        calculatedAt = Date().fromString(body.optString(CALCULATED_AT)),
        ratingTrend = RatingTrend.forRaw(body.optString(RATING_TREND)),
    )
}