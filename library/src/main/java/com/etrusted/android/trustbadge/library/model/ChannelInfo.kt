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

package com.etrusted.android.trustbadge.library.model

import org.json.JSONObject
import com.etrusted.android.trustbadge.library.model.AggregateRating.AggregateRatingPeriod
import com.etrusted.android.trustbadge.library.model.AggregateRating.AggregateRatingDistribution

internal data class ChannelInfo(

    val week: AggregateRating,
    val month: AggregateRating,
    val quarter: AggregateRating,
    val year: AggregateRating,
    val overall: AggregateRating
) {

    companion object {
        private const val KEY_GRADES = "grades"

        private const val KEY_7DAYS = "7days"
        private const val KEY_30DAYS = "30days"
        private const val KEY_90DAYS = "90days"
        private const val KEY_365DAYS = "365days"
        private const val KEY_OVERALL = "overall"
        private const val KEY_COUNT = "count"
        private const val KEY_RATING = "rating"

        private const val KEY_OVERALL_DIST = "distribution"
        private const val KEY_OVERALL_PERIOD = "period"

        fun fromString(body: String): ChannelInfo {
            val bodyJson = JSONObject(body)
            val gradesJson = bodyJson.getJSONObject(KEY_GRADES)

            val response7days = gradesJson.getJSONObject(KEY_7DAYS)
            val response7daysDistribution = response7days.optJSONObject(KEY_OVERALL_DIST)
            val response7daysPeriod = response7days.optJSONObject(KEY_OVERALL_PERIOD)

            val response30days = gradesJson.getJSONObject(KEY_30DAYS)
            val response30daysDistribution = response30days.optJSONObject(KEY_OVERALL_DIST)
            val response30daysPeriod = response30days.optJSONObject(KEY_OVERALL_PERIOD)

            val response90days = gradesJson.getJSONObject(KEY_90DAYS)
            val response90daysDistribution = response90days.optJSONObject(KEY_OVERALL_DIST)
            val response90daysPeriod = response90days.optJSONObject(KEY_OVERALL_PERIOD)

            val response365days = gradesJson.getJSONObject(KEY_365DAYS)
            val response365daysDistribution = response365days.optJSONObject(KEY_OVERALL_DIST)
            val response365daysPeriod = response365days.optJSONObject(KEY_OVERALL_PERIOD)

            val responseOverall = gradesJson.getJSONObject(KEY_OVERALL)
            val responseOverallDistribution = responseOverall.optJSONObject(KEY_OVERALL_DIST)
            val responseOverallPeriod = responseOverall.optJSONObject(KEY_OVERALL_PERIOD)

            return ChannelInfo(
                week = AggregateRating(
                    count = response7days.getInt(KEY_COUNT),
                    rating = response7days.getDouble(KEY_RATING).toFloat(),
                    period = response7daysPeriod?.let { AggregateRatingPeriod.fromJson(it) },
                    distribution = response7daysDistribution?.let { AggregateRatingDistribution
                        .fromJson(it) }
                ),
                month = AggregateRating(
                    count = response30days.getInt(KEY_COUNT),
                    rating = response30days.getDouble(KEY_RATING).toFloat(),
                    period = response30daysPeriod?.let { AggregateRatingPeriod.fromJson(it) },
                    distribution = response30daysDistribution?.let { AggregateRatingDistribution
                        .fromJson(it) }
                ),
                quarter = AggregateRating(
                    count = response90days.getInt(KEY_COUNT),
                    rating = response90days.getDouble(KEY_RATING).toFloat(),
                    period = response90daysPeriod?.let { AggregateRatingPeriod.fromJson(it) },
                    distribution = response90daysDistribution?.let { AggregateRatingDistribution
                        .fromJson(it) },
                ),
                year = AggregateRating(
                    count = response365days.getInt(KEY_COUNT),
                    rating = response365days.getDouble(KEY_RATING).toFloat(),
                    period = response365daysPeriod?.let { AggregateRatingPeriod.fromJson(it) },
                    distribution = response365daysDistribution?.let { AggregateRatingDistribution
                        .fromJson(it) }
                ),
                overall = AggregateRating(
                    count = responseOverall.getInt(KEY_COUNT),
                    rating = responseOverall.getDouble(KEY_RATING).toFloat(),
                    period = responseOverallPeriod?.let {
                        AggregateRatingPeriod.fromJson(it) },
                    distribution = responseOverallDistribution?.let { AggregateRatingDistribution
                        .fromJson(it)
                    },
                ),
            )
        }
    }
}