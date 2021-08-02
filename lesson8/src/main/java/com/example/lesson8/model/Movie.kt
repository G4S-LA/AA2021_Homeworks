package com.example.lesson8.model

import com.example.lesson8.App.Companion.gson
import com.example.lesson8.data.db.entities.MovieEntity
import java.io.Serializable

data class Movie(
    val id: Int,
    val pgAge: Int,
    val title: String,
    val genres: List<Genre>,
    val runningTime: Int,
    val reviewCount: Int,
    val isLiked: Boolean,
    val rating: Float,
    val imageUrl: String?
) : Serializable {
    fun toMovieEntity() = MovieEntity(
        id = id,
        pgAge = pgAge,
        title = title,
        genreEntities = gson.toJson(genres),
        runningTime = runningTime,
        reviewCount = reviewCount,
        isLiked = isLiked,
        rating = rating,
        imageUrl = imageUrl
    )
}