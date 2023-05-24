package com.etrusted.android.trustbadge.library.model

import org.json.JSONObject

internal data class ProductGrade(
    val year: AggregateRating,
) {

    companion object {
        private const val KEY_365DAYS = "365days"
        private const val KEY_COUNT = "count"
        private const val KEY_RATING = "rating"
        private const val KEY_OVERALL_DIST = "distribution"
        private const val KEY_OVERALL_PERIOD = "period"
        private const val KEY_GRADES = "grades"

        fun fromString(body: String): ProductGrade {
            val bodyJson = JSONObject(body)
            val gradesJson = bodyJson.getJSONObject(KEY_GRADES)
            val response365days = gradesJson.getJSONObject(KEY_365DAYS)
            val response365daysDistribution = response365days.optJSONObject(KEY_OVERALL_DIST)
            val response365daysPeriod = response365days.optJSONObject(KEY_OVERALL_PERIOD)

            return ProductGrade(
                year = AggregateRating(
                    count = response365days.getInt(KEY_COUNT),
                    rating = response365days.getDouble(KEY_RATING).toFloat(),
                    period = response365daysPeriod?.let {
                        AggregateRating.AggregateRatingPeriod.fromJson(
                            it
                        )
                    },
                    distribution = response365daysDistribution?.let {
                        AggregateRating.AggregateRatingDistribution
                            .fromJson(it)
                    }
                ),
            )
        }
    }
}