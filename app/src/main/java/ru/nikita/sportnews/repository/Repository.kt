package ru.nikita.sportnews.repository

import retrofit2.Response
import ru.nikita.sportnews.api.RetrofitInstance
import ru.nikita.sportnews.model.News

class Repository {
    suspend fun getNews(): Response<News> {
        return RetrofitInstance.api.getNews()
    }
}
