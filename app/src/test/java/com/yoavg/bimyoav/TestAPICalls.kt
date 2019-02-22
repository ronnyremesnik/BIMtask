package com.yoavg.bimyoav

import com.yoavg.bimyoav.app.Constants
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException
import android.security.NetworkSecurityPolicy
import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements


/**
 * Created by Yoav G on 22/02/2019.
 */

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [23])
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
                Assert.assertEquals(values()[0].articles.size, 10)
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


//    @Implements(NetworkSecurityPolicy::class)
//    class MyNetworkSecurityPolicy {
//
//        val isCleartextTrafficPermitted: Boolean
//            @Implementation
//            get() = true
//
//        companion object {
//
//            val instance: NetworkSecurityPolicy
//                @Implementation
//                get() {
//                    try {
//
//                        val shadow =
//                            MyNetworkSecurityPolicy::class.java.forName("android.security.NetworkSecurityPolicy")
//                        return shadow.newInstance()
//                    } catch (e: Exception) {
//                        throw AssertionError()
//                    }
//
//                }
//        }
//    }

}