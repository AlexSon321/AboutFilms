package com.example.androiddevelopercourse.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androiddevelopercourse.data.FilmRepository
import com.example.androiddevelopercourse.model.FilmItem
import com.example.androiddevelopercourse.model.PopularItem
import kotlinx.coroutines.launch
import retrofit2.Response

class FilmViewModel(application: Application): AndroidViewModel(application) {
    var list: MutableLiveData<Response<FilmItem>> = MutableLiveData()
    var newList: MutableLiveData<Response<PopularItem>> = MutableLiveData()
    var rateList: MutableLiveData<Response<PopularItem>> = MutableLiveData()
    private val repository = FilmRepository()

    fun getFilm(){
        viewModelScope.launch {
            list.value = repository.getFilm()
        }
    }

    fun getNew(main: Int){
        viewModelScope.launch {
            list.value = repository.getNew(main)
        }
    }

    fun getPopular(){
        viewModelScope.launch {
            newList.value = repository.getPopular()
        }
    }

    fun getRated(){
        viewModelScope.launch {
            rateList.value = repository.getRated()
        }
    }




}