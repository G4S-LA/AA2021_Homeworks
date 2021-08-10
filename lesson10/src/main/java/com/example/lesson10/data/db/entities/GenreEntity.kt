package com.example.lesson10.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lesson10.model.Genre
import java.io.Serializable

@Entity(tableName = "genres_table")
data class GenreEntity(

    @PrimaryKey
    val id: Int,
    val name: String
) : Serializable {
    fun toGenre() = Genre(
        id = id,
        name = name
    )
}





















