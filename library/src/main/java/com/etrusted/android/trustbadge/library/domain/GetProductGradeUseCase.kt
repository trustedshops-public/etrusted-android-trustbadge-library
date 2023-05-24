package com.etrusted.android.trustbadge.library.domain

import com.etrusted.android.trustbadge.library.data.repository.IProductGradeRepository
import com.etrusted.android.trustbadge.library.data.repository.ProductGradeRepository
import com.etrusted.android.trustbadge.library.model.ProductGrade

internal interface IGetProductGradeUseCase {
    suspend operator fun invoke(channelId: String, sku: String): Result<ProductGrade>
}

internal class GetProductGradeUseCase(
    private val productGradeRepository: IProductGradeRepository = ProductGradeRepository()
): IGetProductGradeUseCase {

    override suspend fun invoke(
        channelId: String,
        sku: String
    ):Result<ProductGrade> {
        return productGradeRepository.fetchProductGrade(channelId, sku)
    }
}