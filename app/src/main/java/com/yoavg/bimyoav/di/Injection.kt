package com.yoavg.bimyoav.di

import com.yoavg.bimyoav.network.APICalls
import com.yoavg.bimyoav.network.RetrofitClient
import com.yoavg.bimyoav.repository.ArticlesRepository
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Yoav G on 22/02/2019.
 */
class Injection {

    companion object {

        fun injectCompositeDisposable(): CompositeDisposable {
            return CompositeDisposable()
        }

        private fun injectAPICalls(): APICalls {
            return RetrofitClient.newsRetrofit.create(APICalls::class.java)
        }

        fun injectRepository(): ArticlesRepository {
            return ArticlesRepository(injectAPICalls())
        }


    }
}