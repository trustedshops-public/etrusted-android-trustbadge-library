package com.etrusted.android.trustbadge.library.data.datasource

import com.etrusted.android.trustbadge.library.common.internal.ServerResponses
import com.etrusted.android.trustbadge.library.common.internal.getUrlsFor
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.json.JSONException
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ProductGradeDatasourceAndroidTest {


    @Test
    fun testFetchShopGradeDetailReturnsSuccessfully() = runTest {

        // arrange
        val goodData = ServerResponses.ProductGradeGoodResponse.content
        val server = MockWebServer()
        server.enqueue(MockResponse().apply { setBody(goodData) })
        server.start()
        val mockUrl = server.url("")
        val mockUrlRoot = "http://${mockUrl.host}:${mockUrl.port}/"
        val mockUrls = getUrlsFor(mockUrlRoot)
        val sut = ProductGradeDatasource(urls = mockUrls)

        // act
        val result = sut.fetchProductGrade("fakeChannelId", "fakeHexSku")

        // assert
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun testFetchShopGradeDetailFailsCorrectly() = runTest {

        // arrange
        val goodData = ServerResponses.ProductGradeBadResponse.content
        val server = MockWebServer()
        server.enqueue(MockResponse().apply { setBody(goodData) })
        server.start()
        val mockUrl = server.url("")
        val mockUrlRoot = "http://${mockUrl.host}:${mockUrl.port}/"
        val mockUrls = getUrlsFor(mockUrlRoot)
        val sut = ProductGradeDatasource(urls = mockUrls)

        // act
        val result = sut.fetchProductGrade("fakeChannelId", "fakeHexSku")

        // assert
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isNotNull()
        assertThat(result.exceptionOrNull()).isInstanceOf(JSONException::class.java)
    }

}