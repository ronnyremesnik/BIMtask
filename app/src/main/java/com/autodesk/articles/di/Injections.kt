package com.autodesk.articles.di

import com.autodesk.articles.app.BIMApplication
import com.autodesk.articles.data.db.MyRoomDB
import com.autodesk.articles.main_screen.MainScreenDataContract
import com.autodesk.articles.network.APICalls
import com.autodesk.articles.network.RetrofitClient
import com.autodesk.articles.repository.ArticlesRepository
import com.autodesk.articles.repository.LocalDataSource
import com.autodesk.articles.repository.RemoteDataSource
import io.reactivex.disposables.CompositeDisposable


class Injections {

    companion object {

        fun injectCompositeDisposable(): CompositeDisposable {
            return CompositeDisposable()
        }

        private fun injectAPICalls(): APICalls {
            return RetrofitClient.newsRetrofit.create(APICalls::class.java)
        }

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