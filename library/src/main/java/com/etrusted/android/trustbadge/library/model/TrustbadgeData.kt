package com.etrusted.android.trustbadge.library.model

import org.json.JSONObject

data class TrustbadgeData(
    val shop: Shop,
) {
    data class Shop(
        val tsid: String,
        val url: String,
        val name: String,
        val languageISO2: String,
        val targetMarketISO3: String,
        val trustMark: TrustMark,
        val rating: Float? = null,
    ) {
        data class TrustMark(
            val status: String,
            var validFrom: String,
            var validTo: String,
        )
    }

    companion object {

        private const val KEY_RESPONSE = "response"
        private const val KEY_DATA = "data"
        private const val KEY_SHOP = "shop"
        private const val KEY_TRUST_MARK = "trustMark"
        private const val KEY_TSID = "tsId"
        private const val KEY_URL = "url"
        private const val KEY_NAME = "name"
        private const val KEY_LANG_ISO2 = "languageISO2"
        private const val KEY_TARGET_MARKET_ISO3 = "targetMarketISO3"
        private const val KEY_STATUS = "status"
        private const val KEY_VALID_FROM = "validFrom"
        private const val KEY_VALID_TO = "validTo"
        fun fromString(body: String): TrustbadgeData {
            val responseJson = JSONObject(body).getJSONObject(KEY_RESPONSE)
            val dataJson = responseJson.getJSONObject(KEY_DATA)
            val shopJson = dataJson.getJSONObject(KEY_SHOP)
            val trustMarkJson = shopJson.getJSONObject(KEY_TRUST_MARK)

            return TrustbadgeData(
                shop = Shop(
                    tsid = shopJson.getString(KEY_TSID),
                    url = shopJson.getString(KEY_URL),
                    name = shopJson.getString(KEY_NAME),
                    languageISO2 = shopJson.getString(KEY_LANG_ISO2),
                    targetMarketISO3 = shopJson.getString(KEY_TARGET_MARKET_ISO3),
                    trustMark = Shop.TrustMark(
                        status = trustMarkJson.getString(KEY_STATUS),

                        // optional attributes
                        validFrom = trustMarkJson.optString(KEY_VALID_FROM),
                        validTo = trustMarkJson.optString(KEY_VALID_TO),
                    )
                )
            )
        }
    }
}