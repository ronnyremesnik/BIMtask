package com.autodesk.articles.api

import androidx.annotation.VisibleForTesting
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okio.Okio
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
object TestingDependencies {

    const val NUMBER_OF_ITEMS = 10

    fun getRetrofit(baseUrl: HttpUrl): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS).build()
            )
            .build()
    }

    // reading our mock json for the test
    fun getResponseFromJson(fileName: String): String {
        val inputStream = javaClass.classLoader
            .getResourceAsStream("mock-data/$fileName.json")
        val source = Okio.buffer(Okio.source(inputStream))
        return source.readString(StandardCharsets.UTF_8)
    }

}