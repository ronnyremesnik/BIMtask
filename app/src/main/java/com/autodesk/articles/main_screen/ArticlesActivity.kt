package com.autodesk.articles.main_screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.autodesk.articles.R
import com.autodesk.articles.app.Constants
import com.autodesk.articles.data.Source
import com.autodesk.articles.ui.article.ArticlesFragment

class ArticlesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onResume() {
        super.onResume()

        val source = intent?.extras?.getParcelable(Constants.SOURCE) as Source?

        if (source != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, ArticlesFragment.newInstance(source.getItemId()), "Articles")
                .commit()
        }

    }

}
