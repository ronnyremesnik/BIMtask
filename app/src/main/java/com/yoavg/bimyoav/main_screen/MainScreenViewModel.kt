package com.yoavg.bimyoav.main_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource
import com.yoavg.bimyoav.data.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainScreenViewModel(
    private val repository: MainScreenDataContract.Repository,
    private val disposables: CompositeDisposable
) : ViewModel() {

    var articlesList = MutableLiveData<List<Article>>()
    val cid = CountingIdlingResource("counting")

    fun getArticlesList(source: String) {
        cid.increment()
        disposables.add(repository.getData(source)
            .subscribeOn(Schedulers.io())
            .map { apiResponse -> apiResponse.articles }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                cid.decrement()
                articlesList.postValue(data) }, { error ->
                cid.decrement()
                Timber.e(error)
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        //clear the disposables when the viewmodel is cleared
        disposables.clear()
    }
}