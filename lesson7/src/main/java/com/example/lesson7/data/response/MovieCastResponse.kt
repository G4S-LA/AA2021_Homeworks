package com.example.lesson7.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieCastResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("cast") val casts: List<CastResponse>,
): Serializable