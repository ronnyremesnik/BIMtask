package com.yoavg.bimyoav.main_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yoavg.bimyoav.data.Article
import com.yoavg.bimyoav.repository.ArticlesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainScreenViewModel : ViewModel() {

    var articlesList = MutableLiveData<List<Article>>()
    private val disposables = CompositeDisposable()
    private val repo: ArticlesRepository = ArticlesRepository()

    fun getArticlesList(source: String) {
        disposables.add(repo.refreshData(source)
            .subscribeOn(Schedulers.io())
            .map { apiResponse -> apiResponse.articles }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data -> articlesList.postValue(data) }, { error ->
                Timber.e(error)
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}