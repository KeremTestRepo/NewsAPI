package com.necatisozer.newsapi.ui.main.sources

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.necatisozer.domain.entity.Source
import com.necatisozer.newsapi.R
import com.necatisozer.newsapi.databinding.FragmentSourcesBinding
import com.necatisozer.newsapi.di.injector
import com.necatisozer.newsapi.extension.viewModels
import com.necatisozer.newsapi.ui.base.BaseFragment
import splitties.arch.lifecycle.observeNotNull

class SourcesFragment : BaseFragment() {
    private lateinit var binding: FragmentSourcesBinding
    private val viewModel by viewModels { injector.sourcesViewModel }
    private val sourcesAdapter: SourcesAdapter by lazy {
        SourcesAdapter().apply {
            clickListener = { onSourceClick(it) }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sources, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        observeViewModels()
    }

    private fun initViews() {
        binding.toolbar.toolbar.setupWithNavController(findNavController())

        binding.sourcesRecyclerView.apply {
            adapter = sourcesAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun observeViewModels() {
        viewLifecycleOwner.observeNotNull(viewModel.sourcesLiveData()) {
            sourcesAdapter.submitList(it)
        }
    }

    private fun onSourceClick(data: Source) {

    }
}