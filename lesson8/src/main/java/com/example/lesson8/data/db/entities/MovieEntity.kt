package com.example.lesson8.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lesson8.App.Companion.gson
import com.example.lesson8.App.Companion.generator
import com.example.lesson8.model.Movie
import java.io.Serializable

@Entity(tableName = "movies_table")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val pgAge: Int,
    val title: String,
    val genreEntities: String,
    val runningTime: Int,
    val reviewCount: Int,
    val isLiked: Boolean,
    val rating: Float,
    val imageUrl: String?
) : Serializable {

    fun toMovie() = Movie(
        id = id,
        pgAge = pgAge,
        title = title,
        genres = gson.fromJson(genreEntities, generator),
        runningTime = runningTime,
        reviewCount = reviewCount,
        isLiked = isLiked,
        rating = rating,
        imageUrl = imageUrl
    )
}