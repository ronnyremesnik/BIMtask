package com.yoavg.bimyoav.article_screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yoavg.bimyoav.R
import com.yoavg.bimyoav.data.Article
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AppCompatActivity() {


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        val article = intent?.extras?.getSerializable("article") as Article
        webview_full_article.settings.javaScriptEnabled = true
        webview_full_article.loadUrl(article.url)
    }
}