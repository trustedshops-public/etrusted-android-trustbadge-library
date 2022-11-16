package com.etrusted.android.trustbadge.library.repository

import com.etrusted.android.trustbadge.library.model.TrustbadgeData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * This class aims on reading the Trustbadge data
 * without using any third party library (e.g. Okhttp, Retrofit, etc...)
 */
class TrustbadgeRepository {

    suspend fun getTrustbadgeData(tsid: String): Result<TrustbadgeData> {

        return withContext(Dispatchers.IO) {

            val url = URL("https://cdn1.api.trustedshops.com" +
                    "/shops/$tsid/mobiles/v1/sdks/ios/trustmarks.json")
            val urlConnection = url.openConnection() as HttpURLConnection

            try {

                val inputStream = BufferedInputStream(urlConnection.inputStream)
                val body = readStream(inputStream)
                val tBadgeData = parseBody(body)
                Result.success(tBadgeData)

            } catch (e: Exception) {

                Result.failure(e)

            } finally {

                urlConnection.disconnect()
            }
        }
    }

    /**
     * Read TrustBadge from the input stream and convert it to model
     */
    private fun readStream(inputStream: BufferedInputStream): String {
        return inputStream.bufferedReader().use { it.readText() }
    }

    private fun parseBody(body: String): TrustbadgeData {
        val responseJson = JSONObject(body)
        val dataJson = responseJson.getJSONObject("data")
        val shopJson = dataJson.getJSONObject("shop")
        val trustMarkJson = shopJson.getJSONObject("trustMark")

        return TrustbadgeData(
            shop = TrustbadgeData.Shop(
                tsid = dataJson.getString("tsid"),
                url = dataJson.getString("url"),
                name = dataJson.getString("name"),
                languageISO2 = dataJson.getString("languageISO2"),
                targetMarketISO3 = dataJson.getString("targetMarketISO3"),
                trustMark = TrustbadgeData.Shop.TrustMark(
                    status = trustMarkJson.getString("status"),
                    validFrom = trustMarkJson.getString("validFrom"),
                    validTo = trustMarkJson.getString("validTo"),
                )
            )
        )
    }
}