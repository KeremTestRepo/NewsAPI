package com.necatisozer.newsapi.ui.main.articles

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.necatisozer.domain.entity.Article
import com.necatisozer.newsapi.R
import com.necatisozer.newsapi.databinding.FragmentSourcesBinding
import com.necatisozer.newsapi.di.injector
import com.necatisozer.newsapi.extension.viewModels
import com.necatisozer.newsapi.ui.base.BaseFragment
import splitties.arch.lifecycle.observeNotNull
import splitties.toast.toast


class ArticlesFragment : BaseFragment() {
    private lateinit var binding: FragmentSourcesBinding
    private val viewModel by viewModels { injector.articlesViewModel }
    val args: ArticlesFragmentArgs by navArgs()
    private val articlesAdapter: ArticlesAdapter by lazy {
        ArticlesAdapter().apply {
            clickListener = { onArticleClick(it) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) viewModel.init(args.sourceId)
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
        binding.toolbar.toolbar.apply {
            setupWithNavController(findNavController())
            title = args.sourceName
        }

        binding.sourcesRecyclerView.apply {
            adapter = articlesAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun observeViewModels() {
        viewLifecycleOwner.observeNotNull(viewModel.articlesLiveData()) {
            articlesAdapter.submitList(it)
        }

        viewLifecycleOwner.observeNotNull(viewModel.newArticlesEvent()) {
            toast(R.string.alert_more_news)
        }
    }

    private fun onArticleClick(data: Article) {
        val uri = Uri.parse(data.url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}