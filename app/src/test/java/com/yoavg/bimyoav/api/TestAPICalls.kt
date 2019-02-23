package com.yoavg.bimyoav.api

import com.yoavg.bimyoav.app.Constants
import com.yoavg.bimyoav.network.APICalls
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException


/**
 * Created by Yoav G on 22/02/2019.
 */

@RunWith(RobolectricTestRunner::class)
class TestAPICalls {



    private lateinit var mockWebServer: MockWebServer
    private lateinit var calls: APICalls

    @Before
    fun init() {
        mockWebServer = MockWebServer()
        calls = TestingDependencies.getRetrofit(mockWebServer.url("/"))
            .create(APICalls::class.java)
    }


    @Test
    fun getArticles() {
        queueResponse {
            setResponseCode(200)
            setBody(TestingDependencies.getResponseFromJson("articles"))
        }

        calls.getNews(Constants.NEWS_API_KEY, Constants.ABC_NEWS_SOURCE)
            .test()
            .run {
                assertNoErrors()
                assertValueCount(1)
                Assert.assertEquals(values()[0].articles.size, TestingDependencies.NUMBER_OF_ITEMS)
                //   Assert.assertEquals(values()[0][0].userName, "Leanne Graham")
                //     Assert.assertEquals(values()[0][0].id, 1)
            }
    }

    private fun queueResponse(block: MockResponse.() -> Unit) {
        mockWebServer.enqueue(MockResponse().apply(block))
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        mockWebServer.shutdown()
    }
}