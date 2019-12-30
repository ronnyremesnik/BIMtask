package com.autodesk.articles.ui.source

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.autodesk.articles.app.Constants
import com.autodesk.articles.data.Source
import com.autodesk.articles.di.Injections
import com.autodesk.articles.main_screen.ArticlesActivity
import com.autodesk.articles.ui.BaseFragment
import com.autodesk.articles.ui.BaseItemAdapter
import kotlinx.android.synthetic.main.fragment_main.*

class SourcesFragment : BaseFragment<Source>(), SourceListener {
    companion object {

        fun newInstance(): SourcesFragment {
            return SourcesFragment()
        }
    }

    private lateinit var viewModel : SourceViewModel

    private var adapter: SourceAdapter =
        SourceAdapter(this)

    override fun getAdapter(): BaseItemAdapter<Source> {
        return adapter
    }

    override fun initViews() {
        super.initViews()

        viewModel = ViewModelProviders.of(
            this, SourceViewModelFactory(
                Injections.injectSourceRepository(),
                Injections.injectCompositeDisposable()
            )
        ).get(SourceViewModel::class.java)


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

    override fun onResume() {
        super.onResume()
        viewModel.getSourcesList()
    }

    override fun onItemClick(entity: Source) {
        val intent = Intent(context, ArticlesActivity::class.java)
        intent.putExtra(Constants.SOURCE, entity)
        context?.startActivity(intent)

    }

}