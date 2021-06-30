package com.example.lesson5

import android.content.Context

fun generateActors(): List<Actor> {

    val ironMan = Actor(
        "Robert Downey Jr.",
        R.drawable.iron_man
    )

    val captainAmerica = Actor(
        "Chris Evans",
        R.drawable.captain_a
    )

    val hulk = Actor(
        "Mark Ruffalo",
        R.drawable.hulk
    )

    val thor = Actor(
        "Chris Hemsworth",
        R.drawable.thor
    )

    return listOf(ironMan, captainAmerica, hulk, thor)
}

fun generateMovies(context: Context): List<Movie> {

    val movie = Movie(
        context.getString(R.string.film_name), context.getString(R.string.age),context.getString(R.string.storyline_text), 4f,
        196, context.getString(R.string.genres),140, true, R.drawable.movie_1_list
    )

    val movie2 = Movie(
        context.getString(R.string.film_name), context.getString(R.string.age),context.getString(R.string.storyline_text), 4f,
        196, context.getString(R.string.genres), 140, true, R.drawable.movie_1_list
    )

    val movie3 = Movie(
        context.getString(R.string.film_name), context.getString(R.string.age),context.getString(R.string.storyline_text), 4f,
        196, context.getString(R.string.genres), 140, true, R.drawable.movie_1_list
    )

    val movie4 = Movie(
        context.getString(R.string.film_name), context.getString(R.string.age),context.getString(R.string.storyline_text), 4f,
        196, context.getString(R.string.genres), 140, true, R.drawable.movie_1_list
    )

    return listOf(movie, movie2, movie3, movie4)
}
