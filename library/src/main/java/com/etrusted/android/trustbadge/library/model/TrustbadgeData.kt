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
        val guarantee: Guarantee,
        val rating: Float? = null,
    ) {
        data class TrustMark(
            val status: String,
            var validFrom: String,
            var validTo: String,
        )

        data class Guarantee (
            val mainProtectionCurrency: String,
            val maxProtectionAmount: String,
            val maxProtectionDuration: String,
        )
    }

    companion object {

        private const val KEY_RESPONSE = "response"
        private const val KEY_DATA = "data"
        private const val KEY_SHOP = "shop"

        // trustmark object
        private const val KEY_TRUST_MARK = "trustMark"
        private const val KEY_TSID = "tsId"
        private const val KEY_URL = "url"
        private const val KEY_NAME = "name"
        private const val KEY_LANG_ISO2 = "languageISO2"
        private const val KEY_TARGET_MARKET_ISO3 = "targetMarketISO3"
        private const val KEY_STATUS = "status"
        private const val KEY_VALID_FROM = "validFrom"
        private const val KEY_VALID_TO = "validTo"

        // guarantee object
        private const val KEY_GUARANTEE = "guarantee"
        private const val KEY_MAIN_PROTECTION_CURRENCY = "mainProtectionCurrency"
        private const val KEY_MAX_PROTECTION_AMOUNT = "maxProtectionAmount"
        private const val KEY_MAX_PROTECTION_DURATION = "maxProtectionDuration"
        fun fromString(body: String): TrustbadgeData {
            val responseJson = JSONObject(body).getJSONObject(KEY_RESPONSE)
            val dataJson = responseJson.getJSONObject(KEY_DATA)
            val shopJson = dataJson.getJSONObject(KEY_SHOP)
            val trustMarkJson = shopJson.getJSONObject(KEY_TRUST_MARK)
            val guaranteeJson = shopJson.optJSONObject(KEY_GUARANTEE)

            return TrustbadgeData(
                shop = Shop(

                    tsid = shopJson.getString(KEY_TSID),
                    url = shopJson.getString(KEY_URL),
                    name = shopJson.getString(KEY_NAME),
                    languageISO2 = shopJson.getString(KEY_LANG_ISO2),
                    targetMarketISO3 = shopJson.getString(KEY_TARGET_MARKET_ISO3),

                    trustMark = Shop.TrustMark(
                        status = trustMarkJson.getString(KEY_STATUS),
                        validFrom = trustMarkJson.optString(KEY_VALID_FROM),
                        validTo = trustMarkJson.optString(KEY_VALID_TO),
                    ),

                    guarantee = Shop.Guarantee(
                        mainProtectionCurrency = guaranteeJson?.optString(
                            KEY_MAIN_PROTECTION_CURRENCY, "") ?: "",
                        maxProtectionAmount = guaranteeJson?.optString(
                            KEY_MAX_PROTECTION_AMOUNT, "0") ?: "0",
                        maxProtectionDuration = guaranteeJson?.optString(
                            KEY_MAX_PROTECTION_DURATION, "0") ?: "0",
                    )
                )
            )
        }
    }
}