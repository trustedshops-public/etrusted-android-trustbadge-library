package com.etrusted.android.trustbadge.library.data.repository

import com.etrusted.android.trustbadge.library.common.internal.getFakeProductDataDetailDatasource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ProductDataRepositoryAndroidTest {

    @Test
    fun testFetchProductDataReturnsSuccessfully() = runTest {
        // arrange
        val fakeSKU = "fakeSKU"
        val expectedHexSku = "66616b65534b55"
        var hexSKU = ""
        val fakeProductDataDatasource = getFakeProductDataDetailDatasource (spyHexSku = {
            hexSKU = it
        })
        val sut = ProductDataRepository(
            productDataDatasource = fakeProductDataDatasource
        )

        // act
        val result = sut.fetchProductData("fakeChannelId", fakeSKU)

        // assert
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isNotNull()
        assertThat(hexSKU).isEqualTo(expectedHexSku)
    }

    @Test
    fun testFetchProductDatasFails() = runTest {
        // arrange
        val fakeMsg = "failed"
        val fakeProductDataDatasource = getFakeProductDataDetailDatasource(
            result = Result.failure(Throwable(fakeMsg))
        )
        val sut = ProductDataRepository(
            productDataDatasource = fakeProductDataDatasource
        )

        // act
        val result = sut.fetchProductData("fakeChannelId", "fakeSku")

        // assert
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isNotNull()
        assertThat(result.exceptionOrNull()?.message).isEqualTo(fakeMsg)
    }
}