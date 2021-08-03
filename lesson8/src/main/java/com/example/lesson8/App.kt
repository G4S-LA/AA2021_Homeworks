package com.example.lesson8

import android.app.Application
import android.content.Context
import com.example.lesson8.data.MovieRepositoryImpl
import com.example.lesson8.model.Actor
import com.example.lesson8.model.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class App: Application() {

    companion object {
        val gson = Gson()
        val generatorGenre = object : TypeToken<List<Genre>>() {}.type!!
        val generatorActor = object : TypeToken<List<Actor>>() {}.type!!
    }

    override fun onCreate() {
        super.onCreate()
        MovieRepositoryImpl.createDatabase(this)
    }
}