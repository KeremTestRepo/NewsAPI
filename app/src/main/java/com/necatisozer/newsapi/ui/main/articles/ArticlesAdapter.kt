package com.necatisozer.newsapi.ui.main.articles

import android.view.ViewGroup
import com.google.android.material.chip.Chip
import com.necatisozer.domain.entity.Article
import com.necatisozer.newsapi.R
import com.necatisozer.newsapi.databinding.ItemArticleBinding
import com.necatisozer.newsapi.extension.inflater
import com.necatisozer.newsapi.extension.loadUrl
import com.necatisozer.newsapi.ui.base.BaseAdapter
import com.necatisozer.newsapi.ui.base.BaseViewHolder
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

class ArticlesAdapter : BaseAdapter<Article, ArticlesViewHolder>() {
    var readListStatusChangeListener: (Article) -> Unit = {}

    override fun onCreateViewHolder(root: ViewGroup) =
        ArticlesViewHolder(ItemArticleBinding.inflate(root.inflater, root, false), readListStatusChangeListener)
}

class ArticlesViewHolder(binding: ItemArticleBinding, private val listener: (Article) -> Unit) :
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

        // TODO: Use 2-way data binding here
        binding.addToReadListChip.setOnCheckedChangeListener { view, isChecked ->
            listener.invoke(data.copy(read = isChecked))
            if (isChecked) binding.addToReadListChip.makeSelected()
            else binding.addToReadListChip.makeUnselected()
        }

        if (data.read) {
            binding.addToReadListChip.makeSelected()
        } else {
            binding.addToReadListChip.makeUnselected()
        }
    }
}

private fun Chip.makeSelected() {
    isChecked = true
    text = context.getString(R.string.button_selected_add_to_read_list)
}

private fun Chip.makeUnselected() {
    isChecked = false
    text = context.getString(R.string.button_unselected_add_to_read_list)
}