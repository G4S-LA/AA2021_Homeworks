package com.example.lesson8.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieDetailsResponse(
    @SerializedName("adult") val adult : Boolean,
    @SerializedName("backdrop_path") val backdropPath : String?,
    @SerializedName("genres") val genres : List<GenreResponse>,
    @SerializedName("id") val id : Int,
    @SerializedName("overview") val overview : String?,
    @SerializedName("vote_average") val voteAverage : Float,
    @SerializedName("revenue") val revenue : Int,
    @SerializedName("runtime") val runtime : Int?,
    @SerializedName("title") val title : String,
): Serializable