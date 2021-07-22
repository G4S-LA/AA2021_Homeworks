package com.example.lesson7.api

import com.example.lesson7.BuildConfig
import com.example.lesson7.api.ApiKeyInterceptor.Companion.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor: Interceptor {

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        val urlBuilder = origin.url.newBuilder()
        val url = urlBuilder
            .addQueryParameter("api_key", API_KEY)
            .build()

        val requestBuilder = origin.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}