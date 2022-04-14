package ru.nikita.sportnews.model

data class News(
    var articles: List<Article>,
    var status: String,
    var totalResults: Int
)