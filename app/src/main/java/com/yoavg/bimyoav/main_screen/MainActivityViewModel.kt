package com.yoavg.bimyoav.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yoavg.bimyoav.data.Article
import com.yoavg.bimyoav.util.publishSubjectToLiveData
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(
    private val repository: MainScreenDataContract.Repository,
    private val disposable: CompositeDisposable
) : ViewModel(), MainScreenDataContract.ViewModel {

    val articlesList: LiveData<List<Article>> by lazy {
        repository.incomingData.publishSubjectToLiveData(disposable)
    }

    private fun refreshData() {
        repository.refreshData()
    }

    override fun getArticlesList() {
        if (articlesList.value == null) {
            repository.getData()
        } else {
            refreshData()
        }
    }

    override fun onCleared() {
        super.onCleared()

        //clear the disposable when the viewmodel is cleared
        disposable.clear()
        repository.disposable.clear()
    }
}