package com.radmanhayati.cafebazar.data.remote

import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {

    @GET("upcoming")
    suspend fun getMovies(
        @Query("page") page: Int
    ): ApiResult<MovieDto>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    }
}