package com.example.lesson8.api

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.lesson8.data.response.*
import retrofit2.http.Path

interface MoviesApi {

    @GET("configuration")
    suspend fun getConfiguration(): ConfigurationResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
    ): MoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
    ): MovieDetailsResponse

    @GET("genre/movie/list")
    suspend fun getGenres(): GenresResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredit(
        @Path("movie_id") id: Int,
    ): MovieCastResponse
}