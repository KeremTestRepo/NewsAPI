package com.necatisozer.data.mapper

import com.necatisozer.data.api.entity.Article
import com.necatisozer.data.api.entity.Source
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
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
    time = publishedAt?.let { ZonedDateTime.parse(it, DateTimeFormatter.ISO_INSTANT) }
)

fun List<Article>.toArticleEntity() = map { it.toArticleEntity() }
