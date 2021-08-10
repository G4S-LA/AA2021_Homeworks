package com.example.lesson9.background

import com.example.lesson9.data.MovieRepositoryImpl

class SyncMoviesImpl: SyncMovies {
    override suspend fun sync() {
        MovieRepositoryImpl.loadMovies()
    }
}