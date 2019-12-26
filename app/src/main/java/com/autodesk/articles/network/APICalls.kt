package com.autodesk.articles.network

import com.autodesk.articles.data.APIResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface APICalls {

    @GET("top-headlines")
    fun getNews(@Query("apiKey") apiKey: String,
                @Query("sources") sources : String) : Single<APIResponse>
}