package com.example.androiddevelopercourse.data

import com.example.androiddevelopercourse.model.FilmItem
import com.example.androiddevelopercourse.model.Popular
import com.example.androiddevelopercourse.model.PopularItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmApi {

    @GET("/3/movie/123?api_key=d866f943f2d70dee6fcd311d094d5720&language=en-US")
    suspend fun getFilm(): Response<FilmItem>

    @GET("/3/movie/{movie_id}?api_key=d866f943f2d70dee6fcd311d094d5720&language=en-US")
    suspend fun getNew(@Path("movie_id") main: Int): Response<FilmItem>

    @GET("/3/movie/popular?api_key=d866f943f2d70dee6fcd311d094d5720&language=en-US&page=1")
    suspend fun getPopular(): Response<PopularItem>

    @GET("/3/movie/top_rated?api_key=d866f943f2d70dee6fcd311d094d5720&language=en-US&page=1")
    suspend fun getRated(): Response<PopularItem>
}