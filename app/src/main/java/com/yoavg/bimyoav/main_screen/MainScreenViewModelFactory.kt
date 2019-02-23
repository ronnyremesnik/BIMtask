package com.yoavg.bimyoav.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Yoav G on 22/02/2019.
 */
@Suppress("UNCHECKED_CAST")
class MainScreenViewModelFactory(private val repository: MainScreenDataContract.Repository,
                                 private val disposables : CompositeDisposable) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(repository, disposables) as T
    }
}