package com.autodesk.articles.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    //"https://newsapi.org/v2/top-headlines?apiKey=bcd1464b23904833abaef7ef08b5ca21&sources=abc-news"
    private const val NEWS_URL = "https://newsapi.org/v2/"

    private val httpClient: OkHttpClient = OkHttpClient.Builder()
        .build()

    val newsRetrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(httpClient)
        .baseUrl(NEWS_URL)
        .build()
}