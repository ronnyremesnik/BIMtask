package com.autodesk.articles.main_screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.autodesk.articles.R
import com.autodesk.articles.ui.article.ArticlesFragment
import com.autodesk.articles.ui.source.SourcesFragment

class SourcesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, SourcesFragment.newInstance(), "Sources")
            .commit()
    }

}
