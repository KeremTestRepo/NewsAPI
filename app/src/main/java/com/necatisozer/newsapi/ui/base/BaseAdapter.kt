package com.necatisozer.newsapi.ui.base

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.necatisozer.newsapi.extension.addRipple

abstract class BaseAdapter<D, H : BaseViewHolder<D, ViewDataBinding>> :
    ListAdapter<D, H>(DiffCallback<D>()) {
    var clickListener: (D) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: H, position: Int) {
        val data = currentList[position]
        holder.apply {
            bindData(data)
            itemView.setOnClickListener { clickListener(data) }
            itemView.addRipple()
        }
    }

    abstract fun onCreateViewHolder(root: ViewGroup): H
}

abstract class BaseViewHolder<D, out B : ViewDataBinding>(protected val binding: B) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun bindData(data: D)
}

class DiffCallback<D> : DiffUtil.ItemCallback<D>() {
    override fun areItemsTheSame(oldItem: D, newItem: D) = true
    override fun areContentsTheSame(oldItem: D, newItem: D) = oldItem == newItem
}
