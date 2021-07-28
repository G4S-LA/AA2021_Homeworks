package com.example.lesson8.data

import android.content.Context
import com.example.lesson8.App.Companion.appContext
import com.example.lesson8.api.ImageUrlAppender.Size
import com.example.lesson8.api.RetrofitInstance.api
import com.example.lesson8.api.RetrofitInstance.imageUrlAppender
import com.example.lesson8.data.db.MoviesDatabase
import com.example.lesson8.data.response.CastResponse
import com.example.lesson8.data.response.GenreResponse
import com.example.lesson8.data.response.MovieDetailsResponse
import com.example.lesson8.data.response.MovieResponse
import com.example.lesson8.model.*

interface MovieRepository {
    suspend fun loadMovies()
    suspend fun loadMovieDetails(movieId: Int)
}

private const val ADULT_AGE = 16
private const val CHILD_AGE = 13

object MovieRepositoryImpl : MovieRepository {

    private lateinit var database: MoviesDatabase
    val localMovies = database.moviesDao().getAllMovies()
    val localGenres = database.moviesDao().getAllGenres()
    val localActors = database.moviesDao().getAllActors()
    val localMovieDetails = database.moviesDao().getAllMoviesDetails()

    suspend fun insert(){
        database.moviesDao().insertMovies(listOf(
            Movie(
            10,
            0,
            "",
            listOf(Genre(1,"test")),
                0,
                0,
                false,
                0f,
            null)
        ))
    }

    override suspend fun loadMovies() {
        loadGenres()
        val movies = getMovies()
        database.moviesDao().insertMovies(movies)
    }

    private suspend fun loadGenres() {
        val result = runCatchingResult { api.getGenres() }

        if (result is Success) {
            val genres = result.data.genres.map { genreResponseToGenre(it) }
            database.moviesDao().insertGenres(genres)
        }
    }

    private suspend fun getMovies(): List<Movie> {
        return api.getUpcomingMovies(page = 1).results.map { movie ->
            movieResponseToMovie(movie, localGenres.value?: emptyList())
        }
    }

    override suspend fun loadMovieDetails(movieId: Int) {
        loadActors(movieId)
        val result = runCatchingResult { getMovieDetails(movieId) }

        if(result is Success) {

        }
    }

    private suspend fun loadActors(movieId: Int) {
        val result = runCatchingResult { api.getMovieCredit(movieId) }

        if (result is Success) {
            val actors = result.data.casts.map { actorResponseToActor(it) }
            database.moviesDao().insertActors(actors)
        }
    }


    private suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val details = api.getMovieDetails(movieId)

        return detailsResponseToMovieDetails(details) /*MovieDetails(
            10,
            0,
            "",
            listOf(Genre(1,"test")),
            0,
            false,
            0f,
            null,
        "",
        emptyList())*/
    }


    fun createDatabase(context: Context) {
        database = MoviesDatabase.getInstance(context)
    }

}

private fun Boolean.toSpectatorAge(): Int = if (this) ADULT_AGE else CHILD_AGE

suspend fun movieResponseToMovie(movie: MovieResponse, genres: List<Genre>) = Movie(
    id = movie.id,
    title = movie.title,
    imageUrl = imageUrlAppender.getImageUrl(movie.posterPath, Size.POSTER),
    rating = movie.voteAverage / 2,
    reviewCount = movie.voteCount,
    pgAge = movie.adult.toSpectatorAge(),
    runningTime = 100,
    isLiked = false,
    genres = genres.filter { genre -> movie.genreIds.contains(genre.id) }
)

fun genreResponseToGenre(genre: GenreResponse) = Genre(genre.id, genre.name)

suspend fun actorResponseToActor(actor: CastResponse) = Actor(
    id = actor.id,
    name = actor.name,
    imageUrl = imageUrlAppender.getImageUrl(actor.profilePath ?: "", Size.PROFILE)
)

suspend fun detailsResponseToMovieDetails(details: MovieDetailsResponse) = MovieDetails(
    id = details.id,
    pgAge = details.adult.toSpectatorAge(),
    title = details.title,
    genres = details.genres.map { Genre(it.id, it.name) },
    reviewCount = details.revenue,
    isLiked = false,
    rating = details.voteAverage / 2,
    detailImageUrl = imageUrlAppender.getImageUrl(
        details.backdropPath ?: "",
        Size.BACKDROP
    ),
    storyLine = details.overview.orEmpty(),
    actors =  .casts.map { actor ->
        Actor(
            id = actor.id,
            name = actor.name,
            imageUrl = imageUrlAppender.getImageUrl(actor.profilePath ?: "", Size.PROFILE)
        )
    }
)
