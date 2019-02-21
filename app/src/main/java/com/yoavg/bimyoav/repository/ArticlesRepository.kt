package com.yoavg.bimyoav.repository

import com.yoavg.bimyoav.APICalls
import com.yoavg.bimyoav.main_screen.MainScreenViewModel
import com.yoavg.bimyoav.RetrofitClient
import com.yoavg.bimyoav.data.APIResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class ArticlesRepository {


    fun refreshData() : Observable<APIResponse> {
        val calls = RetrofitClient.newsRetrofit.create(APICalls::class.java)
        return calls.getNews("bcd1464b23904833abaef7ef08b5ca21", "abc-news")
//        compositeDisposable.add(calls.getNews("bcd1464b23904833abaef7ef08b5ca21", "abc-news")
//            .subscribeOn(Schedulers.io())
//            .map { t -> t.articles }
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe()


//                compositeDisposable . add twitterApi.getTrends(locationId)
//            .subscribeOn(Schedulers.computation())
//            .map({ trendsResponses -> trendsResponses.get(0) })
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ trendsResponse -> view.load(trendsResponse.trends()) },
//                { throwable -> view.error(throwable) })
//        )

//        calls.getNewsCall("bcd1464b23904833abaef7ef08b5ca21", "abc-news").enqueue(object : Callback<APIResponse> {
//            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
//                Timber.e("failure")
//                Timber.e(t)
//            }
//
//            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
//                Timber.e("response")
//                response.body()?.let {
//                    //return@let it
//                    listener.getListData(it.articles)
//                }
//            }
//        })
    }

}