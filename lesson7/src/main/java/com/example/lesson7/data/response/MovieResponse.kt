package com.example.lesson7.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieResponse(
    @SerializedName("poster_path") val posterPath : String,
    @SerializedName("adult") val adult : Boolean,
    @SerializedName("genre_ids") val genreIds : List<Int>,
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("vote_count") val voteCount : Int,
    @SerializedName("vote_average") val voteAverage : Float,
): Serializable