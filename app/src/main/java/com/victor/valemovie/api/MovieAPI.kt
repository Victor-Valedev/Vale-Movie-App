package com.victor.valemovie.api

import com.victor.valemovie.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieAPI {

    @GET("movie/latest?&language=pt-BR")
    suspend fun recoveredMovie() //Response

    @GET("movie/popular?&language=pt-BR")
    suspend fun recoveredPopularMovie(
        @Query("page") page: Int
    ) : Response<MovieResponse>

}