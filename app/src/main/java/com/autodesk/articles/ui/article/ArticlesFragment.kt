package com.autodesk.articles.ui.article

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.autodesk.articles.app.Constants
import com.autodesk.articles.article_screen.ArticleActivity
import com.autodesk.articles.data.Article
import com.autodesk.articles.di.Injections
import com.autodesk.articles.ui.BaseFragment
import com.autodesk.articles.ui.BaseItemAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import timber.log.Timber

class ArticlesFragment : BaseFragment<Article>(), ArticleListener {
    companion object {

        fun newInstance(source: String): ArticlesFragment {
            val fragment = ArticlesFragment()
            val bundle = Bundle()
            bundle.putString(Constants.SOURCE, source)
            fragment.arguments = bundle

            return fragment
        }
    }

    private lateinit var viewModel : ArticlesViewModel

    private var adapter: ArticleAdapter =
        ArticleAdapter(this)

    override fun initViews() {
        super.initViews()

        viewModel = ViewModelProviders.of(
            this, ArticleViewModelFactory(
                Injections.injectArticleRepository(),
                Injections.injectCompositeDisposable()
            )
        ).get(ArticlesViewModel::class.java)


        viewModel.entitiesList.observe(this, Observer {
            it?.let { list ->
                adapter.submitList(list)
                if (list.isNotEmpty()) {
                    if (!idlingResource.isIdleNow) {
                        idlingResource.decrement()
                    }
                    progress_main.visibility = View.GONE
                }
            }
        })
    }

    override fun getAdapter(): BaseItemAdapter<Article> {
        return adapter
    }

    override fun onResume() {
        super.onResume()
        val source = arguments?.getString(Constants.SOURCE)

        if (source != null) {
            viewModel.getArticlesList(source)
        }

    }


    override fun onItemClick(entity: Article) {
        val intent = Intent(context, ArticleActivity::class.java)
        intent.putExtra(Constants.ARTICLE, entity)
        context?.startActivity(intent)
    }

}