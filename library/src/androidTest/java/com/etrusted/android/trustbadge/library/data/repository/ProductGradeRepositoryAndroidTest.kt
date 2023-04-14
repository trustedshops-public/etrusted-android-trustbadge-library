package com.etrusted.android.trustbadge.library.data.repository

import com.etrusted.android.trustbadge.library.common.internal.getFakeProductGradeDetailDatasource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class ProductGradeRepositoryAndroidTest {

    @Test
    fun testFetchProductGradeReturnsSuccessfully() = runTest {
        // arrange
        val fakeProductGradeDatasource = getFakeProductGradeDetailDatasource()
        val sut = ProductGradeRepository(
            productGradeDatasource = fakeProductGradeDatasource
        )

        // act
        val result = sut.fetchProductGrade("fakeChannelId", "fakeSKU")

        // assert
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isNotNull()

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