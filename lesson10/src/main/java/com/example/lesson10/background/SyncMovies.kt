package com.example.lesson10.background

import com.example.lesson10.data.db.entities.MovieEntity

interface SyncMovies {
    suspend fun sync(): MovieEntity?
}