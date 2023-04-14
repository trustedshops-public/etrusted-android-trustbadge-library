package com.etrusted.android.trustbadge.library.data.repository

import com.etrusted.android.trustbadge.library.common.internal.getFakeProductGradeDetailDatasource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class ProductGradeRepositoryAndroidTest {

    @Test
    fun testFetchProductGradeReturnsSuccessfully() = runTest {
        // arrange
        val fakeSKU = "fakeSKU"
        val expectedHexSku = "66616b65534b55"
        var hexSKU = ""
        val fakeProductGradeDatasource = getFakeProductGradeDetailDatasource(spyHexSku = {
            hexSKU = it
        })
        val sut = ProductGradeRepository(
            productGradeDatasource = fakeProductGradeDatasource
        )

        // act
        val result = sut.fetchProductGrade("fakeChannelId", fakeSKU)

        // assert
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isNotNull()
        assertThat(hexSKU).isEqualTo(expectedHexSku)
    }

    @Test
    fun testFetchProductGradesFails() = runTest {
        // arrange
        val fakeMsg = "failed"
        val fakeProductGradeDatasource = getFakeProductGradeDetailDatasource(
            result = Result.failure(Throwable(fakeMsg))
        )
        val sut = ProductGradeRepository(
            productGradeDatasource = fakeProductGradeDatasource
        )

        // act
        val result = sut.fetchProductGrade("fakeChannelId", "fakeSku")

        // assert
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isNotNull()
        assertThat(result.exceptionOrNull()?.message).isEqualTo(fakeMsg)
    }
}