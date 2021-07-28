package com.example.lesson8.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "movies_details_table")
data class MovieDetailsEntity(
    @PrimaryKey
    val id: Int,
    val pgAge: Int,
    val title: String,
    val reviewCount: Int,
    val isLiked: Boolean,
    val rating: Float,
    val detailImageUrl: String?,
    val storyLine: String
): Serializable {

}