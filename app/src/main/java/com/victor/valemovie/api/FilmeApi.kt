package com.victor.valemovie.api


interface FilmeApi {

    @GET("movie/latest?&language=pt-BR")
    suspend fun recoveredMovie()

}