package com.example.lesson8

import android.app.Application
import android.content.Context
import com.example.lesson8.data.MovieRepositoryImpl
import com.example.lesson8.data.response.MovieResponse
import com.example.lesson8.model.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class App: Application() {

    companion object {
        var appContext: Context? = null
        val gson = Gson()
        val generator = object : TypeToken<List<Genre>>() {}.type!!
    }

    override fun onCreate() {
        super.onCreate()
        MovieRepositoryImpl.createDatabase(this)
    }
}