package com.example.lesson7.model

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
) : Serializable