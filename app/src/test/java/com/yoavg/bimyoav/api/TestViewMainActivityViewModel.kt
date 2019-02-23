package com.yoavg.bimyoav.api

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.yoavg.bimyoav.app.Constants
import com.yoavg.bimyoav.data.Article
import com.yoavg.bimyoav.main_screen.MainActivityViewModel
import com.yoavg.bimyoav.main_screen.MainScreenDataContract
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by Yoav G on 22/02/2019.
 */

@RunWith(RobolectricTestRunner::class)
class TestViewMainActivityViewModel {

    private lateinit var viewModel: MainActivityViewModel
    private var repo : MainScreenDataContract.Repository = mock()
    private var dataChannel : Observer<List<Article>> = mock()
    private val dataSource = Constants.BUZZFEED_SOURCE

    @Before
    fun init() {
        viewModel = MainActivityViewModel(repo, CompositeDisposable())
        viewModel.articlesList.observeForever(dataChannel)
    }

    /**
     * Verify [ListViewModel.refreshPosts] triggers [ListDataContract.Repository.refreshPosts]
     * */
    @Test
    fun testRefreshPosts() {
        //todo - check if there's something else to test
        viewModel.getArticlesList(dataSource)
        verify(repo.getData(dataSource))
   //     viewModel.refreshPosts()
   //     verify(repo).refreshPosts()
    }


//    @Test
//    fun test_gettingArticles(){
//
//    }
}