package com.yoavg.bimyoav

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yoavg.bimyoav.repository.ArticlesRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        news_rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val repo = ArticlesRepository()
        news_rv.adapter = NewsAdapter()

    }
}
