package com.example.lesson11.background

import com.example.lesson11.data.MovieRepositoryImpl

class SyncMoviesImpl: SyncMovies {
    override suspend fun sync() {
        MovieRepositoryImpl.loadMovies()
    }
}