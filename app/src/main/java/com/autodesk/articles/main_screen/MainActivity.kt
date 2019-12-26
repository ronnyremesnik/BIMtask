package com.autodesk.articles.main_screen

import android.os.Bundle
import android.view.View.GONE
import android.widget.ProgressBar
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.idling.CountingIdlingResource
import com.autodesk.articles.R
import com.autodesk.articles.di.Injections

class MainActivity : AppCompatActivity(), MainScreenDataContract.View {

    lateinit var viewModel: MainActivityViewModel
    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    override val idlingResource = CountingIdlingResource("counting")

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
                Injections.injectRepository(),
                Injections.injectCompositeDisposable()
            )
        ).get(MainActivityViewModel::class.java)
        idlingResource.increment()
        viewModel.articlesList.observe(this, Observer {
            it?.let { list ->
                adapter.submitList(list)
                if (list.isNotEmpty()) {
                    if (!idlingResource.isIdleNow) {
                        idlingResource.decrement()
                    }
                    progressBar.visibility = GONE
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getArticlesList()
    }
}
