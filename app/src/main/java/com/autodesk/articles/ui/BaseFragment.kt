package com.autodesk.articles.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.idling.CountingIdlingResource
import com.autodesk.articles.R
import com.autodesk.articles.data.Displayable
import kotlinx.android.synthetic.main.fragment_main.*

abstract class BaseFragment<Entity : Displayable> : Fragment(), BaseDataContract.View {

//    private lateinit var recyclerView: RecyclerView
//    private lateinit var progressBar: ProgressBar
//    protected lateinit var adapter : ArticleAdapter
    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    override val idlingResource = CountingIdlingResource("counting")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    abstract fun getAdapter() : BaseItemAdapter<Entity>

    abstract fun setEntities(list: List<Entity>)

    open fun initViews() {
        main_rv.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
//        adapter = ArticleAdapter()
        main_rv.adapter = getAdapter()

//        viewModel = getEntityViewModel()

        idlingResource.increment()
//        viewModel.getEntitiesObserver().observe(this, Observer {
//            it?.let { list ->
//                //                adapter.submitList(list)
//                setEntities(list)
//                if (list.isNotEmpty()) {
//                    if (!idlingResource.isIdleNow) {
//                        idlingResource.decrement()
//                    }
//                    progress_main.visibility = View.GONE
//                }
//            }
//        })

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}