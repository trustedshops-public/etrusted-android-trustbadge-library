package com.etrusted.android.trustbadge.library.data.datasource

import com.etrusted.android.trustbadge.library.common.internal.IUrls
import com.etrusted.android.trustbadge.library.common.internal.Urls
import com.etrusted.android.trustbadge.library.common.internal.readStream
import com.etrusted.android.trustbadge.library.model.ProductGrade
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

internal interface IProductGradeDatasource {
    suspend fun fetchProductGrade(channelId: String, hexSku: String): Result<ProductGrade>
}

@Suppress("BlockingMethodInNonBlockingContext")
internal class ProductGradeDatasource (
    private val urls: IUrls = Urls,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): IProductGradeDatasource {

    override suspend fun fetchProductGrade(channelId: String, hexSku: String): Result<ProductGrade> {
        return withContext(dispatcher) {

            val url = URL(urls.productGradeJsonUrl() +
                    "/feeds/grades/v1/channels/$channelId/products/sku/$hexSku/feed.json")
            val urlConnection = url.openConnection() as HttpURLConnection

            try{
                val inputStream = BufferedInputStream(urlConnection.inputStream)
                val body = readStream(inputStream)
                val tBadgeData = ProductGrade.fromString(body)
                Result.success(tBadgeData)

            } catch (e: Exception) {

                Result.failure(e)

            } finally {

                urlConnection.disconnect()
            }
        }
    }
}