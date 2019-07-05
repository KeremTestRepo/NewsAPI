package com.necatisozer.data.mapper

import com.necatisozer.common.extension.tryParseDate
import com.necatisozer.data.api.entity.Article
import com.necatisozer.data.api.entity.Source
import com.necatisozer.domain.entity.Article as ArticleEntity
import com.necatisozer.domain.entity.Source as SourceEntity

fun Source.toSourceEntity() = SourceEntity(
    id = id,
    name = name,
    description = description
)

fun List<Source>.toSourceEntity() = map { it.toSourceEntity() }

fun Article.toArticleEntity() = ArticleEntity(
    title = title,
    description = description,
    url = url,
    imageUrl = urlToImage,
    time = tryParseDate(publishedAt)
)

fun List<Article>.toArticleEntity() = map { it.toArticleEntity() }
