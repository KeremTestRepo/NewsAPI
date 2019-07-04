package com.necatisozer.newsapi.ui.main.sources

import android.view.ViewGroup
import com.necatisozer.domain.entity.Source
import com.necatisozer.newsapi.databinding.ItemSourceBinding
import com.necatisozer.newsapi.extension.inflater
import com.necatisozer.newsapi.ui.base.BaseAdapter
import com.necatisozer.newsapi.ui.base.BaseViewHolder

class SourcesAdapter : BaseAdapter<Source, SourcesViewHolder>() {
    override fun onCreateViewHolder(root: ViewGroup) =
        SourcesViewHolder(ItemSourceBinding.inflate(root.inflater, root, false))
}

class SourcesViewHolder(binding: ItemSourceBinding) :
    BaseViewHolder<Source, ItemSourceBinding>(binding) {

    override fun bindData(data: Source) {
        binding.title.text = data.name
        binding.description.text = data.description
    }
}