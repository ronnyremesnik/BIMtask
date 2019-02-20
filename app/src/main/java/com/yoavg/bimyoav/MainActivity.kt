package com.yoavg.bimyoav

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yoavg.bimyoav.repository.ArticlesRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        news_rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = NewsAdapter()
        news_rv.adapter = adapter
        viewModel = ViewModelProviders.of(this).get(MainScreenViewModel::class.java)
        viewModel.articlesList.observe(this, Observer {
            it?.let { list ->
                adapter.submitList(list)
            }
        })
        viewModel.getArticlesList()

    }
}
