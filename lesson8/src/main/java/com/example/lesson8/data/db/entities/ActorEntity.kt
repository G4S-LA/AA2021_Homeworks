package com.example.lesson8.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lesson8.model.Actor
import java.io.Serializable

@Entity
data class ActorEntity(

    @PrimaryKey
    val id: Int,
    val name: String,
    val imageUrl: String?
) : Serializable {
    fun toActor() = Actor(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}