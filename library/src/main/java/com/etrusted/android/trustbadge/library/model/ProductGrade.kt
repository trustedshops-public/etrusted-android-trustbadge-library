package com.etrusted.android.trustbadge.library.model

import com.etrusted.android.trustbadge.library.common.internal.fromString
import org.json.JSONObject
import java.util.*

data class ProductGrade(
    val year: AggregateRating,
) {
    data class AggregateRating(
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
                        ProductGrade.AggregateRating.AggregateRatingPeriod.fromJson(
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

internal fun ProductGrade.AggregateRating.AggregateRatingDistribution.Companion.fromJson(body: JSONObject):
        ProductGrade.AggregateRating.AggregateRatingDistribution {
    return ProductGrade.AggregateRating.AggregateRatingDistribution(
        oneStar = body.optInt(ONE_STAR),
        twoStars = body.optInt(TWO_STAR),
        threeStars = body.optInt(THREE_STAR),
        fourStars = body.optInt(FOUR_STAR),
        fiveStars = body.optInt(FIVE_STAR),
    )
}

internal fun ProductGrade.AggregateRating.AggregateRatingPeriod.Companion.fromJson(body: JSONObject): ProductGrade.AggregateRating.AggregateRatingPeriod {
    return ProductGrade.AggregateRating.AggregateRatingPeriod(
        start = Date().fromString(body.getString(START)) ?: Date(),
        end = Date().fromString(body.getString(END)) ?: Date(),
        firstConsideredReviewSubmission = Date().fromString(body.optString(FIRST_SUBMISSION)),
        lastConsideredReviewSubmission = Date().fromString(body.optString(LAST_SUBMISSION)),
        calculatedAt = Date().fromString(body.optString(CALCULATED_AT)),
        ratingTrend = ProductGrade.AggregateRating.AggregateRatingPeriod.RatingTrend.forRaw(
            body.optString(
                RATING_TREND
            )
        ),
    )
}
