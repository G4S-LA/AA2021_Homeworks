package com.example.lesson8.data.response

import com.example.lesson8.data.db.entities.GenreEntity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenreResponse(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
): Serializable {
    fun toGenreEntity() = GenreEntity(id, name)
}