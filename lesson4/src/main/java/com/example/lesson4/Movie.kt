package com.example.lesson4

import android.net.Uri

data class Movie(
    val name: String,
    val age: String,
    val storyLine: String,
    val rating: Float,
    val numReviews: Int,
    val genres: String,
    val duration: Int,
    val isFavorite: Boolean,
    val image: Int)