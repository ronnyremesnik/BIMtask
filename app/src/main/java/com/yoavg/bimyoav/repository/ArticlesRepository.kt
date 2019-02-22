package com.yoavg.bimyoav.repository

import com.yoavg.bimyoav.APICalls
import com.yoavg.bimyoav.RetrofitClient
import com.yoavg.bimyoav.app.Constants
import com.yoavg.bimyoav.data.APIResponse
import io.reactivex.Single

class ArticlesRepository {

    private val calls = RetrofitClient.newsRetrofit.create(APICalls::class.java)

    fun refreshData(source: String): Single<APIResponse> {
        return calls.getNews(Constants.NEWS_API_KEY, source)
    }

}