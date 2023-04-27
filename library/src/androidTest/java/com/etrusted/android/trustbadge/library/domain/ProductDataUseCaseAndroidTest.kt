package com.etrusted.android.trustbadge.library.domain

import com.etrusted.android.trustbadge.library.common.internal.getFakeProductDataRepository
import com.etrusted.android.trustbadge.library.model.ProductData
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ProductDataUseCaseAndroidTest {
    @Test
    fun testProductDataUseCaseReturnsSuccessfully() = runTest {

        // arrange
        val fakeProductDataRepository = getFakeProductDataRepository()
        val sut = GetProductDataUseCase(
            productDataRepository = fakeProductDataRepository
        )

        // act
        val result = sut(channelId = "fakeString", sku = "fakeSku")

        // assert
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).apply {
            isNotNull()
            isInstanceOf(ProductData::class.java)
        }
    }

    @Test
    fun testProductDataUseCaseFailsCorrectly() = runTest {

        // arrange
        val fakeMsg = "failed"
        val fakeProductDataRepository = getFakeProductDataRepository(
            result = Result.failure(Throwable(fakeMsg))
        )
        val sut = GetProductDataUseCase(
            productDataRepository = fakeProductDataRepository
        )

        // act
        val result = sut(channelId = "fakeString", sku = "fakeSku")

        // assert
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isNotNull()
        assertThat(result.exceptionOrNull()?.message).isEqualTo(fakeMsg)
    }
}