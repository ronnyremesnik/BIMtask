package com.yoavg.bimyoav.repository

import com.yoavg.bimyoav.app.Constants
import com.yoavg.bimyoav.data.APIResponse
import com.yoavg.bimyoav.main_screen.MainScreenDataContract
import com.yoavg.bimyoav.network.APICalls
import io.reactivex.Single

/**
 * Created by Yoav G on 23/02/2019.
 */
class RemoteDataSource(private val calls:APICalls) : MainScreenDataContract.RemoteDataSource {

    override fun getArticles(): Single<APIResponse> {
        return calls.getNews(Constants.NEWS_API_KEY, Constants.ABC_NEWS_SOURCE)
    }
}