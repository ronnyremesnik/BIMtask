package com.yoavg.bimyoav.repository

import com.yoavg.bimyoav.APICalls
import com.yoavg.bimyoav.MainScreenViewModel
import com.yoavg.bimyoav.RetrofitClient
import com.yoavg.bimyoav.data.APIResponse
import com.yoavg.bimyoav.data.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class ArticlesRepository {


    fun refreshData(listener : MainScreenViewModel.CallListener)  {
        val calls = RetrofitClient.newsRetrofit.create(APICalls::class.java)
        calls.getNewsCall("bcd1464b23904833abaef7ef08b5ca21", "abc-news").enqueue(object : Callback<APIResponse> {
            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                Timber.e("failure")
                Timber.e(t)
            }

            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                Timber.e("response")
                response.body()?.let {
                    //return@let it
                    listener.getListData(it.articles)
                }
            }
        })
    }

}