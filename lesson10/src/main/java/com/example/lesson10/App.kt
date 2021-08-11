package com.example.lesson10

import android.app.Application
import android.content.Context
import com.example.lesson10.background.SyncMovies
import com.example.lesson10.background.SyncMoviesImpl
import com.example.lesson10.data.MovieRepositoryImpl
import com.example.lesson10.model.Actor
import com.example.lesson10.model.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class App: Application() {

    companion object {
        const val SYNC_ID = "sync"
        const val PREFERENCE_SYNC = "PREFERENCE_SYNC"
        const val SCHEMA = "com.example.lesson10://"
        const val PATH = "movie/"
        val gson = Gson()
        val generatorGenre = object : TypeToken<List<Genre>>() {}.type!!
        val generatorActor = object : TypeToken<List<Actor>>() {}.type!!
        val synchronizer: SyncMovies = SyncMoviesImpl()
    }

    override fun onCreate() {
        super.onCreate()
        MovieRepositoryImpl.createDatabase(this)
    }
}