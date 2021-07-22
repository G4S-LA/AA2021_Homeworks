package com.example.lesson8.model

import java.io.Serializable

data class MovieDetails(
    val id: Int,
    val pgAge: Int,
    val title: String,
    val genres: List<Genre>,
    val reviewCount: Int,
    val isLiked: Boolean,
    val rating: Float,
    val detailImageUrl: String?,
    val storyLine: String,
    val actors: List<Actor>
): Serializable