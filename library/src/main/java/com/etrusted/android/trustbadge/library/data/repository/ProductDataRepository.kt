package com.etrusted.android.trustbadge.library.data.repository

import com.etrusted.android.trustbadge.library.data.datasource.IProductDataDatasource
import com.etrusted.android.trustbadge.library.data.datasource.ProductDataDatasource
import com.etrusted.android.trustbadge.library.model.ProductData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


internal interface IProductDataRepository {
    suspend fun fetchProductData(channelId: String, sku: String): Result<ProductData>
}

internal class ProductDataRepository
constructor(
    private val productDataDatasource: IProductDataDatasource = ProductDataDatasource(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
): IProductDataRepository
{
    @Throws
    override suspend fun fetchProductData(
        channelId: String,
        sku: String
    ): Result<ProductData> {
        return withContext(dispatcher) {
            val hexSku = sku.toHex()
            return@withContext productDataDatasource.fetchProductData(
                channelId = channelId,
                hexSku = hexSku)
        }
    }
}

private fun String.toHex(): String {
    var output = ""
    for (char in this.chars()) {
        output += char.toString(16)
    }
    return output
}
