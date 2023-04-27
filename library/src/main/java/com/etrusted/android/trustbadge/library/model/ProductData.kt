package com.etrusted.android.trustbadge.library.model

import org.json.JSONObject

data class ProductData(
    var id: String,
    var name: String,
    var url: String,
    var channelId: String,
    var accountId: String,
    var sku: String,
    var gtin: String,
    var mpn: String,
    var brand: String,
    var image: ProductImage
) {

    data class ProductImage(
        var original: ProductImageVersion,
        var questionnaire: ProductImageVersion,
        var hubPage: ProductImageVersion,
    ) {
        data class ProductImageVersion(
            var url: String,
            var width: Int,
            var height: Int
        ) {
            companion object {
                internal const val URL = "url"
                internal const val WIDTH = "width"
                internal const val HEIGHT = "height"
            }
        }

        companion object {
            private const val KEY_ORIGINAL = "original"
            private const val KEY_HUBPAGE = "hubPage"
            private const val KEY_QUESTIONAIRE = "productReviewQuestionnaire"

            fun fromString(body: String): ProductImage {
                val imageJson = JSONObject(body)
                val responseOriginal = imageJson.getJSONObject(KEY_ORIGINAL)
                val responseHubpage = imageJson.getJSONObject(KEY_HUBPAGE)
                val responseQuestionaire = imageJson.getJSONObject(KEY_QUESTIONAIRE)

                return ProductImage(
                    original = responseOriginal.let {
                        ProductImageVersion.fromJson(
                            it
                        )
                    },
                    hubPage = responseHubpage.let {
                        ProductImageVersion.fromJson(
                            it
                        )
                    },
                    questionnaire = responseQuestionaire.let {
                        ProductImageVersion.fromJson(
                            it
                        )
                    }
                )
            }
        }
    }

    companion object {

        fun fromString(body: String): ProductData {
            val bodyJson = JSONObject(body)

            return ProductData(
                id = bodyJson.getString("id"),
                name = bodyJson.getString("name"),
                url = bodyJson.getString("url"),
                channelId = bodyJson.getString("channelId"),
                accountId = bodyJson.getString("accountId"),
                sku = bodyJson.getString("sku"),
                gtin = bodyJson.getString("gtin"),
                mpn = bodyJson.getString("mpn"),
                brand = bodyJson.getString("brand"),
                image = ProductImage.fromString(bodyJson.getString("image"))
            )
        }
    }
}

internal fun ProductData.ProductImage.ProductImageVersion.Companion.fromJson(body: JSONObject): ProductData.ProductImage.ProductImageVersion {
    return ProductData.ProductImage.ProductImageVersion(
        url = body.getString(URL),
        width = body.getInt(WIDTH),
        height = body.getInt(HEIGHT)
    )
}