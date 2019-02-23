package com.yoavg.bimyoav.main_screen

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yoavg.bimyoav.R
import com.yoavg.bimyoav.di.Injections

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel
    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progress_main)
        progressBar.visibility = VISIBLE
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
        viewModel.articlesList.observe(this, Observer {
            it?.let { list ->
                adapter.submitList(list)
                if (list.isNotEmpty()) {
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
