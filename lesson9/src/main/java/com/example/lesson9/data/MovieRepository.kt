package com.example.lesson9.data

import android.content.Context
import com.example.lesson9.App.Companion.gson
import com.example.lesson9.api.ImageUrlAppender.Size
import com.example.lesson9.api.RetrofitInstance.api
import com.example.lesson9.api.RetrofitInstance.imageUrlAppender
import com.example.lesson9.data.db.MoviesDatabase
import com.example.lesson9.data.db.entities.*
import com.example.lesson9.data.response.*
import com.example.lesson9.model.*
import kotlinx.coroutines.Dispatchers

interface MovieRepository {
    suspend fun loadMovies()
    suspend fun loadMovieDetails(movieId: Int)
}

private const val ADULT_AGE = 16
private const val CHILD_AGE = 13

object MovieRepositoryImpl : MovieRepository {

    private lateinit var database: MoviesDatabase
    val localMovies by lazy(Dispatchers.IO) { database.moviesDao().getAllMovies() }
    private val localGenres by lazy(Dispatchers.IO) { database.moviesDao().getAllGenres() }

    override suspend fun loadMovies() {
        val result = runCatchingResult { getMovies() }

        if (result is Success) {
            val movies = result.data
            database.moviesDao().insertMovies(movies.map { it.toMovieEntity() })
        }
    }

    private suspend fun getMovies(): List<Movie> {
        loadGenres()

        return api.getUpcomingMovies(page = 1).results.map { movie ->
            movieResponseToMovie(movie, localGenres.value?.map { it.toGenre() } ?: emptyList())
        }
    }

    private suspend fun loadGenres() {
        val genres = api.getGenres().genres.map { it.toGenreEntity() }
        database.moviesDao().insertGenres(genres)
    }

    override suspend fun loadMovieDetails(movieId: Int) {
        val result = runCatchingResult { getMovieDetails(movieId) }

        if (result is Success) {
            database.moviesDao().insertMovieDetails(result.data)
        }
    }

    private suspend fun getMovieDetails(movieId: Int): MovieDetailsEntity {
        val details = api.getMovieDetails(movieId)
        val actors = api.getMovieCredit(movieId)

        return detailsResponseToMovieDetailsEntity(details, actors)
    }

    fun createDatabase(context: Context) {
        database = MoviesDatabase.getInstance(context)
    }

    fun getLocalMovieDetails(id: Int) = database.moviesDao().getMovieDetails(id)

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

suspend fun actorResponseToActorEntity(actor: CastResponse) = Actor(
    id = actor.id,
    name = actor.name,
    imageUrl = imageUrlAppender.getImageUrl(actor.profilePath ?: "", Size.PROFILE)
)

suspend fun detailsResponseToMovieDetailsEntity(
    details: MovieDetailsResponse,
    actors: MovieCastResponse
) = MovieDetailsEntity(
    id = details.id,
    pgAge = details.adult.toSpectatorAge(),
    title = details.title,
    genres = gson.toJson(details.genres),
    reviewCount = details.revenue,
    isLiked = false,
    rating = details.voteAverage / 2,
    detailImageUrl = imageUrlAppender.getImageUrl(
        details.backdropPath ?: "",
        Size.BACKDROP
    ),
    storyLine = details.overview.orEmpty(),
    actors = gson.toJson(actors.casts.map { actorResponseToActorEntity(it) })
)
