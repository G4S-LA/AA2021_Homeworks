package com.example.lesson8.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImageResponse(
    @SerializedName("secure_base_url") val secureBaseUrl : String,
    @SerializedName("backdrop_sizes") val backdropSizes : List<String>,
    @SerializedName("poster_sizes") val posterSizes : List<String>,
    @SerializedName("profile_sizes") val profileSizes : List<String>,
): Serializable