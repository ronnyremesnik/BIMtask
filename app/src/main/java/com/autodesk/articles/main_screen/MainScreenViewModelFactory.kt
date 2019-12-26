package com.autodesk.articles.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.disposables.CompositeDisposable

@Suppress("UNCHECKED_CAST")
class MainScreenViewModelFactory(private val repository: MainScreenDataContract.Repository,
                                 private val disposables : CompositeDisposable) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(repository, disposables) as T
    }
}