package com.victor.valemovie.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = ""
    const val IMAGE_URL = "https://image.tmdb.org/t/p/"
    const val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwNmViMjZlY2U1YWMyOGRmZTQxZDBlYzMxYmE2YTBiYyIsInN1YiI6IjY2MTQ4NzMyNTkwMDg2MDBiNDdjNWY1YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.I8auAOUiiRJwhBhv5kJ4fHYgnIA2_HJ9e_CQ0b_lUV4"

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .writeTimeout(10,TimeUnit.SECONDS)
        .readTimeout(20,TimeUnit.SECONDS)
        .connectTimeout(20,TimeUnit.SECONDS)
        .addInterceptor(AuthInterceptor())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val movieAPI = retrofit.create(MovieAPI::class.java)



}