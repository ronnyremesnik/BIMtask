package com.yoavg.bimyoav.viewmodel

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.yoavg.bimyoav.data.Article
import com.yoavg.bimyoav.main_screen.MainActivityViewModel
import com.yoavg.bimyoav.main_screen.MainScreenDataContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class TestViewMainActivityViewModel {

    private lateinit var viewModel: MainActivityViewModel
    private var repo : MainScreenDataContract.Repository = mock()
    private var dataChannel : Observer<List<Article>> = mock()

    @Before
    fun init() {
        viewModel = MainActivityViewModel(repo, CompositeDisposable())
        whenever(repo.incomingData).doReturn(PublishSubject.create())
        viewModel.articlesList.observeForever(dataChannel)
    }

    // test getting the list triggers the repo
    @Test
    fun test_getArticlesList() {
        viewModel.getArticlesList()
        verify(repo).getData()
    }


}