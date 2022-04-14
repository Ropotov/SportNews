package ru.nikita.sportnews.model

data class Article(
    var author: Any,
    var content: String,
    var description: String,
    var title: String,
    var url: String,
    var urlToImage: String
)