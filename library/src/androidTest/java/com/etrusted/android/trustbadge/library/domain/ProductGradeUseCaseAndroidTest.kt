package com.etrusted.android.trustbadge.library.domain

import com.etrusted.android.trustbadge.library.common.internal.getFakeProductGradeRepository
import com.etrusted.android.trustbadge.library.model.ProductGrade
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ProductGradeUseCaseAndroidTest {
    @Test
    fun testProductGradeUseCaseReturnsSuccessfully() = runTest {

        // arrange
        val fakeProductGradeRepository = getFakeProductGradeRepository()
        val sut = GetProductGradeUseCase(
            productGradeRepository = fakeProductGradeRepository
        )

        // act
        val result = sut(channelId = "fakeString", sku = "fakeSku")

        // assert
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).apply {
            isNotNull()
            isInstanceOf(ProductGrade::class.java)
        }
    }

    @Test
    fun testProductGradeUseCaseFailsCorrectly() = runTest {

        // arrange
        val fakeMsg = "failed"
        val fakeProductGradeRepository = getFakeProductGradeRepository(
            result = Result.failure(Throwable(fakeMsg))
        )
        val sut = GetProductGradeUseCase(
            productGradeRepository = fakeProductGradeRepository
        )

        // act
        val result = sut(channelId = "fakeString", sku = "fakeSku")

        // assert
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isNotNull()
        assertThat(result.exceptionOrNull()?.message).isEqualTo(fakeMsg)
    }
}