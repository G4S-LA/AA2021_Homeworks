package com.example.lesson10.background

import com.example.lesson10.data.MovieRepositoryImpl

class SyncMoviesImpl: SyncMovies {
    override suspend fun sync() {
        MovieRepositoryImpl.loadMovies()
    }
}