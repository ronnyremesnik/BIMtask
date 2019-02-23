package com.yoavg.bimyoav.main_screen

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.test.espresso.idling.CountingIdlingResource
import com.yoavg.bimyoav.data.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainActivityViewModel(
    private val repository: MainScreenDataContract.Repository,
    private val disposables: CompositeDisposable
) : ViewModel(), MainScreenDataContract.ViewModel {

    var articlesList = MutableLiveData<List<Article>>()

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    override val idlingResource = CountingIdlingResource("counting")

    override fun getArticlesList(source: String) {
        // for testing, increment before a blocking operation and decrement after
        idlingResource.increment()
        disposables.add(repository.getData(source)
            .subscribeOn(Schedulers.io())
            .map { apiResponse -> apiResponse.articles }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                idlingResource.decrement()
                articlesList.postValue(data)
            }, { error ->
                // don't forget to decrement on all use cases
                idlingResource.decrement()
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