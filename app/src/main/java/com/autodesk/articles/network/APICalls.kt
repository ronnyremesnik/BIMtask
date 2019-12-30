package com.autodesk.articles.network

import com.autodesk.articles.data.ArticlesResponse
import com.autodesk.articles.data.SourceResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface APICalls {

    @GET("top-headlines")
    fun getNews(@Query("apiKey") apiKey: String,
                @Query("sources") sources : String) : Single<ArticlesResponse>

    @GET("sources")
    fun getSources(@Query("apiKey") apiKey: String) : Single<SourceResponse>

}