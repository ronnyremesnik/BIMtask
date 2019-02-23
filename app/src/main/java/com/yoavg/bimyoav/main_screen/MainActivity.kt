package com.yoavg.bimyoav.main_screen

import android.os.Bundle
import android.view.View.GONE
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yoavg.bimyoav.NewsAdapter
import com.yoavg.bimyoav.R
import com.yoavg.bimyoav.app.Constants
import com.yoavg.bimyoav.di.Injection
import timber.log.Timber
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel
    lateinit var newsRecyclerView: RecyclerView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progress_main)
        newsRecyclerView = findViewById(R.id.news_rv)
        newsRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = NewsAdapter()
        newsRecyclerView.adapter = adapter
        viewModel = ViewModelProviders.of(
            this, MainScreenViewModelFactory(
                Injection.injectRepository(),
                Injection.injectCompositeDisposable()
            )
        ).get(MainActivityViewModel::class.java)
        viewModel.articlesList.observe(this, Observer {
            it?.let { list ->
                adapter.submitList(list)
                progressBar.visibility = GONE
            }
        })
    }

    override fun onResume() {
        super.onResume()
        if (Random.nextBoolean()) {
            Timber.d("getting list from abc")
            viewModel.getArticlesList(Constants.ABC_NEWS_SOURCE)
        } else {
            Timber.d("getting list from buzzfeed")
            viewModel.getArticlesList(Constants.BUZZFEED_SOURCE)
        }
    }
}
