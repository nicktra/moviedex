package com.nicktra.moviedex.core.data.source.remote.network

import com.nicktra.moviedex.core.data.source.remote.response.ListMovieResponse
import com.nicktra.moviedex.core.data.source.remote.response.ListShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getMovieList(@Query("api_key") apiKey: String): ListMovieResponse

    @GET("tv/popular")
    suspend fun getShowList(@Query("api_key") apiKey: String): ListShowResponse
}