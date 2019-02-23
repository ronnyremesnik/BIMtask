package com.yoavg.bimyoav.di

import com.yoavg.bimyoav.app.BIMApplication
import com.yoavg.bimyoav.data.db.MyRoomDB
import com.yoavg.bimyoav.main_screen.MainScreenDataContract
import com.yoavg.bimyoav.network.APICalls
import com.yoavg.bimyoav.network.RetrofitClient
import com.yoavg.bimyoav.repository.ArticlesRepository
import com.yoavg.bimyoav.repository.LocalDataSource
import com.yoavg.bimyoav.repository.RemoteDataSource
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Yoav G on 22/02/2019.
 */
class Injections {

    companion object {

        fun injectCompositeDisposable(): CompositeDisposable {
            return CompositeDisposable()
        }

        private fun injectAPICalls(): APICalls {
            return RetrofitClient.newsRetrofit.create(APICalls::class.java)
        }

//        fun injectRepository(): ArticlesRepository {
//            return ArticlesRepository(injectAPICalls())
//        }

        private fun injectDatabase() : MyRoomDB {
            return MyRoomDB.getInstance(BIMApplication.applicationContext())
        }

        private fun injectLocalDataSource() : MainScreenDataContract.LocalDataSource {
            return LocalDataSource(injectDatabase())
        }

        private fun injectRemoteDataSource() : MainScreenDataContract.RemoteDataSource {
            return RemoteDataSource(injectAPICalls())
        }

        fun injectRepository(): MainScreenDataContract.Repository {
            return ArticlesRepository(injectLocalDataSource(), injectRemoteDataSource())
        }
    }
}