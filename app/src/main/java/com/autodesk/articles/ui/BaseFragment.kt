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

abstract class BaseFragment<Entity : Displayable> : Fragment() {

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    val idlingResource = CountingIdlingResource("counting")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    abstract fun getAdapter() : BaseItemAdapter<Entity>

    open fun initViews() {
        main_rv.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        main_rv.adapter = getAdapter()
        idlingResource.increment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}