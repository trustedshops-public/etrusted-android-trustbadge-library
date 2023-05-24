package com.etrusted.android.trustbadge.library.domain

import com.etrusted.android.trustbadge.library.data.repository.IProductDataRepository
import com.etrusted.android.trustbadge.library.data.repository.ProductDataRepository
import com.etrusted.android.trustbadge.library.model.ProductData

internal interface IGetProductDataUseCase {
    suspend operator fun invoke(channelId: String, sku: String): Result<ProductData>
}

internal class GetProductDataUseCase(
    private val productDataRepository: IProductDataRepository = ProductDataRepository()
): IGetProductDataUseCase {

    override suspend fun invoke(
        channelId: String,
        sku: String
    ):Result<ProductData> {
        return productDataRepository.fetchProductData(channelId, sku)
    }
}