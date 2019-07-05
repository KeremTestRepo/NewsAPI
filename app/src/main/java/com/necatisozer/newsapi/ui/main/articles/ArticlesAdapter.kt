package com.necatisozer.newsapi.ui.main.articles

import android.view.ViewGroup
import com.necatisozer.domain.entity.Article
import com.necatisozer.newsapi.databinding.ItemArticleBinding
import com.necatisozer.newsapi.extension.inflater
import com.necatisozer.newsapi.extension.loadUrl
import com.necatisozer.newsapi.ui.base.BaseAdapter
import com.necatisozer.newsapi.ui.base.BaseViewHolder
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

class ArticlesAdapter : BaseAdapter<Article, ArticlesViewHolder>() {
    override fun onCreateViewHolder(root: ViewGroup) =
        ArticlesViewHolder(ItemArticleBinding.inflate(root.inflater, root, false))
}

class ArticlesViewHolder(binding: ItemArticleBinding) :
    BaseViewHolder<Article, ItemArticleBinding>(binding) {

    override fun bindData(data: Article) {
        data.imageUrl?.let { binding.image.loadUrl(it) }
        binding.title.text = data.title
        data.time?.let {
            val time = DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG)
                .format(it)
                .removeSuffix(" Z")

            binding.time.text = time
        }
    }
}