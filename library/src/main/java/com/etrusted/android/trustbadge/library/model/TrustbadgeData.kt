package com.etrusted.android.trustbadge.library.model

import java.util.Date

data class TrustbadgeData(
    val shop: Shop,
) {
    data class Shop(
        val tsid: String,
        val url: String,
        val name: String,
        val languageISO2: String,
        val targetMarketISO3: String,
        val trustMark: TrustMark
    ) {
        data class TrustMark(
            val status: String,
            val validFrom: String,
            val validTo: String,
        )
    }
}