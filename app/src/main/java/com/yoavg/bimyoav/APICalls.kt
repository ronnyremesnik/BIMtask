package com.yoavg.bimyoav

import com.yoavg.bimyoav.data.APIResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface APICalls {

    @GET("top-headlines")
    fun getNews(@Query("apiKey") apiKey: String,
                @Query("sources") sources : String) : Observable<APIResponse>


    @GET("top-headlines")
    fun getNewsCall(@Query("apiKey") apiKey: String,
                @Query("sources") sources : String) : Call<APIResponse>

}