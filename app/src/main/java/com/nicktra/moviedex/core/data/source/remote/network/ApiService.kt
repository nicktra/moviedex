package com.nicktra.moviedex.core.data.source.remote.network

import com.nicktra.moviedex.core.data.source.remote.response.ListMovieResponse
import com.nicktra.moviedex.core.data.source.remote.response.ListShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getMovieList(@Query("api_key") apiKey: String): Call<ListMovieResponse>

    @GET("tv/popular")
    fun getShowList(@Query("api_key") apiKey: String): Call<ListShowResponse>
}