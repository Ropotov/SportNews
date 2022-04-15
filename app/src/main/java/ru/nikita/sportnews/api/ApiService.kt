package ru.nikita.sportnews.api

import retrofit2.Response
import retrofit2.http.GET
import ru.nikita.sportnews.model.News

interface ApiService {
    @GET("top-headlines?country=ru&category=sports&apiKey=9066a74bf5e9468caea43082b64719ee")
    suspend fun getNews(): Response<News>
}
