package com.example.lesson7.api

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.lesson7.api.RetrofitInstance.API_KEY
import retrofit2.http.Path

interface MoviesApi {

    @GET("/configuration")
    suspend fun getConfiguration()

    @GET("/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") key: String = API_KEY,
    )

    @GET("/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") key: String = API_KEY,
    )

    @GET("/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") key: String = API_KEY,
    )


    @GET("/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") key: String = API_KEY,
    )

    @GET("/movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") key: String = API_KEY,
    )

    @GET("/person/{person_id}")
    suspend fun getActor(
        @Path("person_id") id: Int,
        @Query("api_key") key: String = API_KEY,
    )
    
}