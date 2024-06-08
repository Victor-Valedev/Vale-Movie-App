package com.victor.valemovie.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val constructorRequest = chain.request().newBuilder()

        val request = constructorRequest.addHeader(
            "Authorization",
            "Bearer ${RetrofitService.TOKEN}"
        ).build()

        return chain.proceed(request)
    }
}