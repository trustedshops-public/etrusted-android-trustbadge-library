package com.etrusted.android.trustbadge.library.data.datasource

import com.etrusted.android.trustbadge.library.common.internal.IUrls
import com.etrusted.android.trustbadge.library.common.internal.Urls
import com.etrusted.android.trustbadge.library.common.internal.readStream
import com.etrusted.android.trustbadge.library.model.ProductData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

internal interface IProductDataDatasource {
    suspend fun fetchProductData(channelId: String, hexSku: String): Result<ProductData>
}

@Suppress("BlockingMethodInNonBlockingContext")
internal class ProductDataDatasource (
    private val urls: IUrls = Urls,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): IProductDataDatasource {

    override suspend fun fetchProductData(channelId: String, hexSku: String): Result<ProductData> {
        return withContext(dispatcher) {

            val url = URL(urls.productDataJsonUrl() +
                    "/feeds/products/v1/channels/$channelId/sku/$hexSku/feed.json")
            val urlConnection = url.openConnection() as HttpURLConnection

            try{
                val inputStream = BufferedInputStream(urlConnection.inputStream)
                val body = readStream(inputStream)
                val tBadgeData = ProductData.fromString(body)
                Result.success(tBadgeData)

            } catch (e: Exception) {

                Result.failure(e)

            } finally {

                urlConnection.disconnect()
            }
        }
    }
}