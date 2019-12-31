package com.autodesk.articles.viewmodel

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.autodesk.articles.data.Article
import com.autodesk.articles.ui.articles.ArticlesViewModel
import com.autodesk.articles.ui.MainScreenDataContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class TestViewArticlesActivityViewModel {

    private lateinit var viewModel: ArticlesViewModel
    private var repo : MainScreenDataContract.Repository = mock()
    private var dataChannel : Observer<List<Article>> = mock()

    @Before
    fun init() {
        viewModel = ArticlesViewModel(repo, CompositeDisposable())
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