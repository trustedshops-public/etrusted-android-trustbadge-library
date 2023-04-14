package com.etrusted.android.trustbadge.library.data.repository

import com.etrusted.android.trustbadge.library.data.datasource.IProductGradeDatasource
import com.etrusted.android.trustbadge.library.data.datasource.ProductGradeDatasource
import com.etrusted.android.trustbadge.library.model.ProductGrade
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


internal interface IProductGradeRepository {
    suspend fun fetchProductGrade(channelId: String, sku: String): Result<ProductGrade>
}

internal class ProductGradeRepository
constructor(
    private val productGradeDatasource: IProductGradeDatasource = ProductGradeDatasource(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
): IProductGradeRepository
{
    @Throws
    override suspend fun fetchProductGrade(
        channelId: String,
        sku: String
    ): Result<ProductGrade> {
        return withContext(dispatcher) {
            val hexSku = sku.toHex()
            return@withContext productGradeDatasource.fetchProductGrade(
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
