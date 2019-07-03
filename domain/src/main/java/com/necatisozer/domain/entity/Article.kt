package com.necatisozer.domain.entity

import org.threeten.bp.ZonedDateTime

data class Article(
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val time: ZonedDateTime
)