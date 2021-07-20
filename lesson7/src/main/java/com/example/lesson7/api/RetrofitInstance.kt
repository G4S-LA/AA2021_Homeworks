package com.example.lesson7.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "06d6cb823d6b37952d2dab5da2f365bf"

    private val logger by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val okHttp = OkHttpClient.Builder().addInterceptor(logger)

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: MoviesApi by lazy {
        retrofit.create(MoviesApi::class.java)
    }

    val imageUrlAppender by lazy {
        ImageUrlAppender()
    }
}