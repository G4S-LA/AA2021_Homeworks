package com.example.lesson7.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CastResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("profile_path") val profilePath: String?,
): Serializable