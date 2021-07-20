package com.example.lesson7.api

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.lesson7.api.RetrofitInstance.API_KEY
import com.example.lesson7.data.response.*
import retrofit2.http.Path

interface MoviesApi {

    @GET("configuration")
    suspend fun getConfiguration(
        @Query("api_key") key: String = API_KEY,
    ): ConfigurationResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
        @Query("api_key") key: String = API_KEY,
    ): MoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") key: String = API_KEY,
    ): MovieDetailsResponse

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") key: String = API_KEY,
    ): GenresResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredit(
        @Path("movie_id") id: Int,
        @Query("api_key") key: String = API_KEY,
    ): MovieCastResponse
}