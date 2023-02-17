package com.example.androiddevelopercourse.data

import com.example.androiddevelopercourse.model.FilmItem
import com.example.androiddevelopercourse.model.PopularItem
import retrofit2.Response

class FilmRepository {

    suspend fun getFilm(): Response<FilmItem>{
        return RetrofitInstance.api.getFilm()
    }

    suspend fun getNew(main: Int): Response<FilmItem>{
        return RetrofitInstance.api.getNew(main)
    }

    suspend fun getPopular(): Response<PopularItem>{
        return RetrofitInstance.api.getPopular()
    }

    suspend fun getRated(): Response<PopularItem>{
        return RetrofitInstance.api.getRated()
    }


}