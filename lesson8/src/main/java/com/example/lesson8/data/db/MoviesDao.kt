package com.example.lesson8.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lesson8.model.Actor
import com.example.lesson8.model.Genre
import com.example.lesson8.model.Movie
import com.example.lesson8.model.MovieDetails

@Dao
interface MoviesDao {

    @Insert(entity = Movie::class)
    suspend fun insertMovies(movies: List<Movie>)

    @Insert(entity = Actor::class)
    suspend fun insertActors(actors: List<Actor>)

    @Insert(entity = Genre::class)
    suspend fun insertGenres(genres: List<Genre>)

    @Insert(entity = MovieDetails::class)
    suspend fun insertMovieDetails(movies: List<MovieDetails>)

    @Query("SELECT * FROM movies_table")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM genres_table")
    fun getAllGenres(): LiveData<List<Genre>>

    @Query("SELECT * FROM actors_table")
    fun getAllActors(): LiveData<List<Actor>>

    @Query("SELECT * FROM movies_details_table")
    fun getAllMoviesDetails(): LiveData<List<MovieDetails>>

    @Query("SELECT * FROM genres_table WHERE id = :id")
    suspend fun getGenre(id: Int): Genre

    @Query("SELECT * FROM actors_table WHERE id = :id")
    suspend fun getActor(id: Int): Actor

    @Query("SELECT * FROM movies_details_table WHERE id = :id")
    suspend fun getMovieDetails(id: Int): MovieDetails

}