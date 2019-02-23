package com.yoavg.bimyoav.article_screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.yoavg.bimyoav.R
import com.yoavg.bimyoav.app.Constants
import com.yoavg.bimyoav.data.Article

class ArticleActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var rootView: ConstraintLayout

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        val article = intent?.extras?.getParcelable(Constants.ARTICLE) as Article
        webView = findViewById(R.id.webview_full_article)
        progressBar = findViewById(R.id.web_progress_bar)
        rootView = findViewById(R.id.article_layout)
        // some abc-news articles need JS so they can load properly
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                rootView.removeViewInLayout(progressBar)
                // changing visibility wouldn't do since going to background and returning
                // brought back the progressbar
            }
        }
        webView.loadUrl(article.url)
    }
}