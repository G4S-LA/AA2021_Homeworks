package com.example.lesson7.data

import android.annotation.SuppressLint
import com.example.lesson7.api.RetrofitInstance.api
import com.example.lesson7.api.RetrofitInstance.imageUrlAppender
import com.example.lesson7.model.*

interface MovieRepository {
    suspend fun loadMovies(): Result<List<Movie>>
    suspend fun loadMovieDetails(movieId: Int): Result<MovieDetails>
}

private const val ADULT_AGE = 16
private const val CHILD_AGE = 13

@SuppressLint("StaticFieldLeak")
object MovieRepositoryImpl: MovieRepository {

    override suspend fun loadMovies(): Result<List<Movie>> {
        return runCatchingResult { getMovies() }
    }

    private suspend fun getMovies(): List<Movie> {
        val genres = api.getGenres().genres

        return api.getUpcomingMovies(page = 1).results.map { movie ->
            Movie(
                id = movie.id,
                title = movie.title,
                imageUrl = imageUrlAppender.getMoviePosterImageUrl(movie.posterPath),
                rating = movie.voteAverage / 2,
                reviewCount = movie.voteCount,
                pgAge = movie.adult.toSpectatorAge(),
                runningTime = 100,
                isLiked = false,
                genres = genres
                    .filter { genreResponse -> movie.genreIds.contains(genreResponse.id) }
                    .map { genre -> Genre(genre.id, genre.name) },
            )
        }
    }

    override suspend fun loadMovieDetails(movieId: Int): Result<MovieDetails> {
        return runCatchingResult { getMovieDetails(movieId) }
    }

    private suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val details = api.getMovieDetails(movieId)

        return MovieDetails(
            id = details.id,
            pgAge = details.adult.toSpectatorAge(),
            title = details.title,
            genres = details.genres.map { Genre(it.id, it.name) },
            reviewCount = details.revenue,
            isLiked = false,
            rating = details.voteAverage / 2,
            detailImageUrl = imageUrlAppender.getDetailImageUrl(details.backdropPath),
            storyLine = details.overview.orEmpty(),
            actors = api.getMovieCredit(movieId).casts.map { actor ->
                Actor(
                    id = actor.id,
                    name = actor.name,
                    imageUrl = imageUrlAppender.getActorImageUrl(actor.profilePath)
                )
            }
        )
    }

}

private fun Boolean.toSpectatorAge(): Int = if (this) ADULT_AGE else CHILD_AGE