package ru.nikita.sportnews.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.nikita.sportnews.model.News
import ru.nikita.sportnews.repository.Repository

class MyViewModel : ViewModel() {
    private val repository = Repository()
    val myNewsList: MutableLiveData<Response<News>> = MutableLiveData()

    fun getMyNews() {
        viewModelScope.launch {
            myNewsList.value = repository.getNews()
        }
    }
}
