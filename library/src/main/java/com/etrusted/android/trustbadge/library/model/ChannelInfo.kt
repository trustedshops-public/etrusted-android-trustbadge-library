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

package com.etrusted.android.trustbadge.library.model

import org.json.JSONObject
import java.util.Date

internal data class ChannelInfo(

    val week: AggregateRating,
    val month: AggregateRating,
    val quarter: AggregateRating,
    val year: AggregateRating,
    val overall: AggregateRating
) {
    data class AggregateRating(
        val count: Int,
        val rating: Float,
        val distribution: AggregateRatingDistribution? = null,
        val period: AggregateRatingPeriod? = null,
    ) {
        data class AggregateRatingDistribution(
            val oneStar: Int,
            val twoStars: Int,
            val threeStars: Int,
            val fourStars: Int,
            val fiveStars: Int
        )
        data class AggregateRatingPeriod(
            val start: Date,
            val end: Date,
            val firstConsideredReviewSubmission: Date?,
            val lastConsideredReviewSubmission: Date?,
            val calculatedAt: Date?,
            val ratingTrend: RatingTrend?
        ) {
            enum class RatingTrend { NEGATIVE, NEUTRAL, POSITIVE }
        }
    }

    companion object {
        private const val KEY_7DAYS = "7days"
        private const val KEY_30DAYS = "30days"
        private const val KEY_90DAYS = "90days"
        private const val KEY_365DAYS = "365days"
        private const val KEY_OVERALL = "overall"
        private const val KEY_COUNT = "count"
        private const val KEY_RATING = "rating"


        fun fromString(body: String): ChannelInfo {
            val bodyJson = JSONObject(body)
            val response7days = bodyJson.getJSONObject(KEY_7DAYS)
            val response30days = bodyJson.getJSONObject(KEY_30DAYS)
            val response90days = bodyJson.getJSONObject(KEY_90DAYS)
            val response365days = bodyJson.getJSONObject(KEY_365DAYS)
            val responseOverall = bodyJson.getJSONObject(KEY_OVERALL)

            return ChannelInfo(
                week = AggregateRating(
                    count = response7days.getInt(KEY_COUNT),
                    rating = response7days.getDouble(KEY_RATING).toFloat()
                ),
                month = AggregateRating(
                    count = response30days.getInt(KEY_COUNT),
                    rating = response30days.getDouble(KEY_RATING).toFloat()
                ),
                quarter = AggregateRating(
                    count = response90days.getInt(KEY_COUNT),
                    rating = response90days.getDouble(KEY_RATING).toFloat()
                ),
                year = AggregateRating(
                    count = response365days.getInt(KEY_COUNT),
                    rating = response365days.getDouble(KEY_RATING).toFloat()
                ),
                overall = AggregateRating(
                    count = responseOverall.getInt(KEY_COUNT),
                    rating = responseOverall.getDouble(KEY_RATING).toFloat()
                ),
            )
        }
    }
}