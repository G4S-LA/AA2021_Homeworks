package com.example.lesson11

import android.app.Application
import android.content.Context
import com.example.lesson11.background.SyncMovies
import com.example.lesson11.background.SyncMoviesImpl
import com.example.lesson11.data.MovieRepositoryImpl
import com.example.lesson11.model.Actor
import com.example.lesson11.model.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class App: Application() {

    companion object {
        const val SYNC_ID = "sync"
        const val PREFERENCE_SYNC = "PREFERENCE_SYNC"
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