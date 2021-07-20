package com.example.lesson7.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MoviesResponse(
    @SerializedName("page") val page : Int,
    @SerializedName("results") val results : List<MovieResponse>,
): Serializable