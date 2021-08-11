package com.example.lesson10.background

import com.example.lesson10.data.MovieRepositoryImpl
import com.example.lesson10.data.db.entities.MovieEntity

class SyncMoviesImpl: SyncMovies {
    override suspend fun sync(): MovieEntity? {
        return MovieRepositoryImpl.sync()
    }

}