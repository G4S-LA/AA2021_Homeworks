package com.example.lesson11.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lesson11.data.db.entities.*
import com.example.lesson11.model.Actor
import com.example.lesson11.model.Genre
import com.example.lesson11.model.Movie
import com.example.lesson11.model.MovieDetails

@Dao
interface MoviesDao {

    @Insert(entity = MovieEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(entity = GenreEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<GenreEntity>)

    @Insert(entity = MovieDetailsEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(movie: MovieDetailsEntity)

    @Query("SELECT * FROM movies_table")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM genres_table")
    fun getAllGenres(): LiveData<List<GenreEntity>>

    @Query("SELECT * FROM movies_details_table WHERE id = :id")
    suspend fun getMovieDetails(id: Int): MovieDetailsEntity

}