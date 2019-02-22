package com.yoavg.bimyoav.repository

import com.yoavg.bimyoav.network.APICalls
import com.yoavg.bimyoav.app.Constants
import com.yoavg.bimyoav.data.APIResponse
import com.yoavg.bimyoav.main_screen.MainScreenDataContract
import io.reactivex.Single

class ArticlesRepository(private val calls: APICalls) : MainScreenDataContract.Repository {

    override fun getData(source: String): Single<APIResponse> {
        return calls.getNews(Constants.NEWS_API_KEY, source)
    }


}